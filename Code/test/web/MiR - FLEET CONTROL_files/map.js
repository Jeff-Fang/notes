function MirMap(canvas_element) { // canvas_element is jQuery element, like $("#canvas")

	var _this = this;
	this.canvas_element = canvas_element;
	this.map_container = this.canvas_element.parent().parent();
	this.canvas = null;
	this.uid = this.generateUID();
	this.max_zoom_level = 2;
	this.min_zoom_level = 1.0;
	this.last_drag_x = null;
	this.last_drag_y = null;
	this.object_menu = null;

	// Map variables, only 1 map is used per canvas
	this.map_id = null;
	this.map_origin_x = null;
	this.map_origin_y = null;
	this.map_origin_theta = null;
	this.map_resolution = null;
	this.map_resscale = null;

	// Object prefixes
	this.map_prefix = 'MAP_';
	this.robot_prefix = 'ROBOT_';
	this.position_prefix = 'POSITION_';

	// Position type colors
	this.pos_colors = new Array();
	this.pos_colors[0] = "#4199D4"; // Robot position
	this.pos_colors[1] = "#33cc33"; // Trolley position
	this.pos_colors[2] = "#33cc33"; // Trolley pickup position
	this.pos_colors[3] = "#33cc33"; // Trolley left entry
	this.pos_colors[4] = "#33cc33"; // Trolley right entry
	this.pos_colors[5] = "#FF8000"; // Shelf position
	this.pos_colors[6] = "#FF8000"; // Shelf entry position
	this.pos_colors[7] = "#008080"; // Charging station
	this.pos_colors[8] = "#33cc33"; // Charging station entry
	this.pos_colors[9] = "#008080"; // V-marker position
	this.pos_colors[10] = "#008080"; // V-marker position entry
	this.pos_colors[11] = "#008080"; // VL-marker position
	this.pos_colors[12] = "#008080"; // VL-marker position entry
	this.pos_colors[42] = "#00afaf"; // staging position

	// Set up fabricjs canvas
	this.canvas = new fabric.Canvas(this.canvas_element.get(0), {
		selection: false,
		scale: 1,
		renderOnAddRemove: true,
		moveCursor: 'default',
		hoverCursor: 'default',
		backgroundColor: '#CDCDCD'
	});


	// Set up events
	var panning = false;
	this.canvas.on({
		'after:render': function(e){
			_this.moveObjectMenu();
		},
		'mouse:up': function(e){
			panning = false;
		},
		'mouse:down': function(e){
			panning = true;
			_this.last_drag_x = e.e.pageX;
			_this.last_drag_y = e.e.pageY;
		},
		'touch:drag': function(e) {
			if (panning && e && e.e) {
				var delta = new fabric.Point((e.e.pageX - _this.last_drag_x), (e.e.pageY - _this.last_drag_y));
		        _this.canvas.relativePan(delta);
				_this.last_drag_x = e.e.pageX;
				_this.last_drag_y = e.e.pageY;
		    }
		},
		'touch:gesture': function(e){
			// Handle zoom only if 2 fingers are touching the screen
			if (e.e.touches && e.e.touches.length == 2) {
			    // Get event point
			    var point = new fabric.Point(e.self.x, e.self.y);
			    // Remember canvas scale at gesture start
			    if (e.self.state == "start") {
					zoomStartScale = _this.canvas.getZoom();
			    }
			    // Calculate delta from start scale
			    var delta = zoomStartScale * e.self.scale;
			    // Zoom to pinch point
			    _this.canvas.zoomToPoint(point, delta);
			}
		}
	});


	// Bind scroll event to zoom function
	this.canvas_element.next().bind('mousewheel DOMMouseScroll', function(e){
		return _this.handleScroll(e);
	});

	// Bind resize event to resize canvas function
	$(window).bind('resize', function(e){
		_this.resizeCanvas();
	});

	this.resizeCanvas();
	this.canvas.setHeight(0);

}

MirMap.prototype.resizeCanvas = function() {
	this.canvas.setWidth(this.map_container.width());
}

MirMap.prototype.handleScroll = function(e){

	var direction = null;
	var zoom_factor = 1.07;

	// Determine if scroll up or down
	if(e.type == 'mousewheel'){
		var evt = window.event || e;
		evt = evt.originalEvent ? evt.originalEvent : evt;
		var delta = evt.detail ? evt.detail*(-40) : evt.wheelDelta;
		
		if(delta > 0) {
			direction = 'up';
		}else if(delta < 0){
			direction = 'down';
		}
	}else{
		if(e.originalEvent.detail < 0) {
			direction = 'up';
		}else if(e.originalEvent.detail > 0){
			direction = 'down';
		}
	}

	if(direction == 'up'){

		if(this.canvas.getZoom() == this.max_zoom_level){
			// Already at max, scroll on document instead of canvas
			return true;
		}else{
			var new_zoom = this.canvas.getZoom() * zoom_factor;
			if(new_zoom >= this.max_zoom_level){
				new_zoom = this.max_zoom_level;
			}
		}

	}else if(direction == 'down'){

		if(this.canvas.getZoom() == this.min_zoom_level){
			// Already at min, scroll on document instead of canvas
			return true;
		}else{
			var new_zoom = this.canvas.getZoom() / zoom_factor;
			if(new_zoom <= this.min_zoom_level){
				new_zoom = this.min_zoom_level;
			}
		}
	}
	
	this.canvas.zoomToPoint(new fabric.Point(e.offsetX, e.offsetY), new_zoom);

	return false;
}

MirMap.prototype.setMap = function(map_id, origin_x, origin_y, origin_theta, resolution){

	var _this = this;
	this.removeMap(); // Remove map if one is already set

	// Store variables
	this.map_id = map_id; // Store the map id, this way it is easier to delete the map again
	this.map_resscale = 1.0 / resolution;
	this.map_origin_x = (this.map_resscale) * origin_x;
	this.map_origin_y = (this.map_resscale) * origin_y;
	this.map_theta = origin_theta;
	this.map_resolution = resolution; // Store the map id, this way it is easier to delete the map again

	fabric.Image.fromURL("/get-map-png.php?id="+map_id, function(img) {

		// Store the original size of the image
		_this.map_height = img.height;
		_this.map_width = img.width;

		_this.canvas.setHeight(img.height); // Set the canvas size to the same height as the map
		_this.canvas.add(img.set({
			uid: _this.map_prefix+map_id,
			left: 0,
			top: 0,
			angle: 0,
			selection: false,
			hasControls: false,
			hasBorders: false,
			hasRotatingPoint: false,
			lockMovementX: true,
			lockMovementY: true,
			lockScalingFlip: true,
			lockScalingX: true,
			lockScalingY: true,
			lockSkewingX: true,
			lockSkewingY: true,
			lockUniScaling: true,
			lockRotation: true
		}));
		img.sendToBack();
		
		_this.image = img;
		
		var zoomLevel = 1;
		if(img.width > _this.canvas.getWidth())
		{
			// Zoom
			zoomLevel = _this.canvas.getWidth() / img.width;

			if (zoomLevel < _this.min_zoom_level)
				_this.min_zoom_level = zoomLevel;

			_this.canvas.setZoom(zoomLevel);
			
			// Handle height
			_this.canvas.setHeight(img.height*zoomLevel);
		}
		else
		{
			// Reset pan
			var delta = new fabric.Point(0, 0);
			_this.canvas.absolutePan(delta);

			// Pan image to center of container
			var delta = new fabric.Point(((_this.map_container.width()/2) - (img.width/2)), 0);
			_this.canvas.relativePan(delta);
		}

		_this.moveAllObjectsAfterMapLoad();
	});

}

MirMap.prototype.getMapID = function(){
	return this.map_id;
}

MirMap.prototype.removeMap = function(){
	this.removeObjectByUID(this.map_prefix+this.map_id);
}

MirMap.prototype.clearMap = function(){
	this.canvas.clear();
}

// This is not tested on a robot
MirMap.prototype.setLaserMap = function(url){

	var _this = this;
	fabric.Image.fromURL(url, function(img) {

		_this.removeLaserMap(); // Remove map if one is already set
		_this.canvas.add(img.set({
			uid: "LASERMAP",
			left: 0,
			top: 0,
			angle: 0,
			selection: false,
			hasControls: false,
			hasBorders: false,
			hasRotatingPoint: false,
			lockMovementX: true,
			lockMovementY: true,
			lockScalingFlip: true,
			lockScalingX: true,
			lockScalingY: true,
			lockSkewingX: true,
			lockSkewingY: true,
			lockUniScaling: true,
			lockRotation: true
		}));

	});

}

MirMap.prototype.removeLaserMap = function(){
	this.removeObjectByUID("LASERMAP");
}

// This is not tested on a robot
MirMap.prototype.setGridMap = function(url){

	var _this = this;
	fabric.Image.fromURL(url, function(img) {

		var x = 0;
		var y = 0;

		// Find robot - we assume there is only one
		_this.canvas.forEachObject(function(obj, i){
			if (obj.uid.substring(0, 6) == "ROBOT_") {
				x = obj.pos_x;
				y = obj.pos_y;
			}
		});

		var coordinates = _this.convertToCoordinates(x, y);

		_this.removeGridMap(); // Remove map if one is already set
		_this.canvas.add(img.set({
			uid: "GRIDMAP",
			pos_x: x,
			pos_y: y,
			left: coordinates.x,
			top: coordinates.y,
			originX: 'center',
			originY: 'center',
			angle: 0,
			selection: false,
			hasControls: false,
			hasBorders: false,
			hasRotatingPoint: false,
			lockMovementX: true,
			lockMovementY: true,
			lockScalingFlip: true,
			lockScalingX: true,
			lockScalingY: true,
			lockSkewingX: true,
			lockSkewingY: true,
			lockUniScaling: true,
			lockRotation: true
		}));

	});

}

MirMap.prototype.removeGridMap = function(){
	this.removeObjectByUID("GRIDMAP");
}

MirMap.prototype.addRobot = function(robot_id, pos_x, pos_y, theta, robot_name){

	// Check if robot exits, do not add if it already exists
	if(this.getObjectIndexByUID(this.robot_prefix+robot_id) != -1){
		return false;
	}

	var rect = new fabric.Rect({
        width: 20,
        height: 12,
        left: 0,
        top: 0,
        fill: 'blue'
    });

	var rect2 = new fabric.Rect({
        width: 8,
        height: 2,
        left: 12,
        top: 5,
        fill: 'yellow'
    });

	var coordinates = this.convertToCoordinates(pos_x, pos_y);

	var group = new fabric.Group([ rect, rect2 ],{
		uid: this.robot_prefix+robot_id,
		pos_x: pos_x,
		pos_y: pos_y,
		left: coordinates.x,
		top: coordinates.y,
		angle: -theta,
		originX: 'center',
		originY: 'center',
		selection: false,
		hasControls: false,
		hasBorders: false,
		hasRotatingPoint: false,
		lockMovementX: true,
		lockMovementY: true,
		hoverCursor: 'pointer',
		lockScalingFlip: true,
		lockScalingX: true,
		lockScalingY: true,
		lockSkewingX: true,
		lockSkewingY: true,
		lockUniScaling: true,
		lockRotation: true
	});

	var _this = this;

	// Mouse events
	group.on('mousedown', function(e){

		if(_this.isObjectMenuVisible() && group.uid == _this.object_menu.uid){
			_this.hideObjectMenu();
		}else{
			_this.showObjectMenu(group, robot_name);
		}
	});

	group.on('mouseover', function(e){

	});

	group.on('mouseout', function(e){

	});

	this.canvas.add(group);
	group.bringToFront();

}

MirMap.prototype.convertToCoordinates = function(pos_x, pos_y){

	return {
		x: (pos_x * this.map_resscale) - this.map_origin_x,
		y: this.map_height - (pos_y * this.map_resscale - this.map_origin_y)
	}

}

MirMap.prototype.removeRobot = function(robot_id){
	this.removeObjectByUID(this.robot_prefix+robot_id);
}

MirMap.prototype.moveRobot = function(robot_id, pos_x, pos_y, theta){
	var index = this.getObjectIndexByUID(this.robot_prefix+robot_id);
	if(index != -1){
		var coordinates = this.convertToCoordinates(pos_x, pos_y);
		this.canvas.item(index).set({left: coordinates.x, top: coordinates.y, angle: -theta}).bringToFront();
		this.canvas.renderAll();
	}
}


// This annoying function is to make sure that all objects are correctly rendered even if the map is loaded after the other objects
MirMap.prototype.moveAllObjectsAfterMapLoad = function(){

	var _this = this;
	this.canvas.forEachObject(function(obj, i){
		if (obj.uid.substring(0, 4) != "MAP_") {

			var x = _this.canvas.item(i).pos_x;
			var y = _this.canvas.item(i).pos_y;

			// Only convert to coordinates if object actually has coordinates
			if(x == undefined){
				x = 0;
				y = 0;
				_this.canvas.item(i).set({left: 0, top: 0});
			}else{
				var coordinates = _this.convertToCoordinates(x, y);
				_this.canvas.item(i).set({left: coordinates.x, top: coordinates.y});
			}

		}
	});

	this.canvas.renderAll();

}

MirMap.prototype.addPosition = function(position_id, pos_x, pos_y, theta, position_name, type_id){

	if(this.getObjectIndexByUID(this.position_prefix+position_id) != -1){
		return false;
	}

	var color = this.pos_colors[type_id];

	var circle = new fabric.Circle({
	    left: 0,
	    top: 0,
	    radius: 10,
	    strokeWidth: 1,
	    fill: '#FFF',
	    stroke: color,
	    selectable: false,
	    originX: 'center',
		originY: 'center'
	});

	var line_left = new fabric.Line([ 5, 0, 0, 5], {
		left: -5,
		top: -4,
		selectable: false,
		stroke: color
    });

	var line_right = new fabric.Line([ 0, 0, 5, 5], {
		left: 0,
		top: -4,
		selectable: false,
		stroke: color
	});

	var coordinates = this.convertToCoordinates(pos_x, pos_y);
	var theta = -theta+90;

	var group = new fabric.Group([ circle, line_left, line_right ],{
		uid: this.position_prefix+position_id,
		left: coordinates.x,
		top: coordinates.y,
		pos_x: pos_x,
		pos_y: pos_y,
		angle: theta,
		originX: 'center',
		originY: 'center',
		hoverCursor: 'pointer',
		selection: false,
		hasControls: false,
		hasBorders: false,
		hasRotatingPoint: false,
		lockMovementX: true,
		lockMovementY: true,
		lockScalingFlip: true,
		lockScalingX: true,
		lockScalingY: true,
		lockSkewingX: true,
		lockSkewingY: true,
		lockUniScaling: true,
		lockRotation: true
	});

	var _this = this;

	group.on('mousedown', function(e){

		if(_this.isObjectMenuVisible() && group.uid == _this.object_menu.uid){

			_this.hideObjectMenu();

		}else{

			var menu_items = new Array();
			// menu_items['Goto'] = "/position/goto/"+position_id;
			// menu_items['Edit'] = "/position/edit/"+position_id;
			_this.showObjectMenu(group, position_name, menu_items);

		}

	});

	this.canvas.add(group);
	group.bringToFront();

}

MirMap.prototype.removePosition = function(position_id){
	this.removeObjectByUID(this.position_prefix+position_id);
}

MirMap.prototype.movePosition = function(position_id, pos_x, pos_y, theta){
	var index = this.getObjectIndexByUID(this.position_prefix+position_id);
	if(index != -1){
		var coordinates = this.convertToCoordinates(pos_x, pos_y);
		this.canvas.item(index).set({left: coordinates.x, top: coordinates.y, angle: theta}).bringToFront();
		this.canvas.renderAll();
	}
}

// Not working yet
MirMap.prototype.addTrajectory = function(line_coordinates){

	this.removeTrajectory();

	line_parts = [];

	for (var key in line_coordinates) {

		var line = new fabric.Line([ line_coordinates[key][0], line_coordinates[key][1], line_coordinates[key][2], line_coordinates[key][3]], {
			uid: "TRAJECTORY",
			left: line_coordinates[key][0],
			top: line_coordinates[key][1],
			originX: 'left',
			originY: 'top',
			selectable: false,
			stroke: "#FF0000",
			backgroundColor: '#CDCDCD'
		});

		this.canvas.add(line);
		//group.bringToFront();

		//line_parts.push(line);

	}
	/*
	var group = new fabric.Group(line_parts, {
		uid: "TRAJECTORY",
		left: 100,
		top: 100,
		angle: 0,
		backgroundColor: '#CDCDCD',
		originX: 'left',
		originY: 'top',
		selection: false,
		hasControls: false,
		hasBorders: false,
		hasRotatingPoint: false,
		lockMovementX: true,
		lockMovementY: true,
		lockScalingFlip: true,
		lockScalingX: true,
		lockScalingY: true,
		lockSkewingX: true,
		lockSkewingY: true,
		lockUniScaling: true,
		lockRotation: true
	});

	this.canvas.add(group);
	group.bringToFront();
	*/

}

MirMap.prototype.removeTrajectory = function(position_id){
	this.removeObjectByUID("TRAJECTORY");
}


// Finds the object index position based on the uid, return -1 if not found
MirMap.prototype.getObjectIndexByUID = function(uid){
	var pos = -1;
	this.canvas.forEachObject(function(obj, i){
		if(uid == obj.uid){
			pos = i;
			return;
		}
	});
	return pos;
}

// Removes an object by its uid
MirMap.prototype.removeObjectByUID = function(uid){
	var index = this.getObjectIndexByUID(uid);
	if(index != -1){
		var d_object = this.canvas.item(index);
		this.canvas.remove(d_object);
	}
}

MirMap.prototype.hideObjectMenu = function(){
	$("#map_menu_"+this.uid).remove();
}

MirMap.prototype.showObjectMenu = function(object, title, menu_items){

	// Remove the menu if we want to show a new object
	this.hideObjectMenu();

	// Save the object that the menu is bound to
	this.object_menu = object;

	// Add menu items
	var menu_items_html = '';
	if(menu_items != undefined){
		for (var key in menu_items) {
			menu_items_html += '<a class="button tiny" href="'+menu_items[key]+'">'+key+'</a>';
		}
	}

	// Append the
	$("body").append('<div class="map_menu" id="map_menu_'+this.uid+'"><strong>'+title+'</strong>'+menu_items_html+'</div>');

	this.moveObjectMenu();

}

MirMap.prototype.moveObjectMenu = function(){

	if(this.object_menu == null){
		return;
	}

	var map_menu = $("#map_menu_"+this.uid);
	var offset = this.canvas_element.offset();

	// Get right
	var canvas_top = offset.top;
	var object_top = this.canvas.viewportTransform[0] * this.object_menu.top;
	var pan_top = this.canvas.viewportTransform[5];

	// Get left
	var canvas_left = offset.left;
	var object_left = this.canvas.viewportTransform[0] * this.object_menu.left;
	var pan_left = this.canvas.viewportTransform[4];

	// Get the width and height of the menu
	var outer_width = map_menu.outerWidth(true);
	var outer_height = map_menu.outerHeight(true);

	// Final calculations
	var top = canvas_top + object_top + pan_top - (15*this.canvas.viewportTransform[0]) - outer_height;
	var left = canvas_left + object_left + pan_left - (outer_width/2);

	// Set the attributes
	map_menu.css('top', Math.round(top)+'px');
	map_menu.css('left', Math.round(left)+'px');
	map_menu.css('visibility', 'visible');

}

MirMap.prototype.isObjectMenuVisible = function(){
	if($("#map_menu_"+this.uid).is(':visible')){
		return true;
	}else{
		return false;
	}
}

MirMap.prototype.generateUID = function(){
	function s4() {
    	return Math.floor((1 + Math.random()) * 0x10000).toString(16).substring(1);
  	}
  	return s4() + s4() + '-' + s4() + '-' + s4() + '-' + s4() + '-' + s4() + s4() + s4();
}
