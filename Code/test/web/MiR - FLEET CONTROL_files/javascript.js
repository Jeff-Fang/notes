var auto_refresh_reference = [];
$(function(){

	// Top menu
	var WINDOW_CHANGE_EVENT = ('onorientationchange' in window) ? 'orientationchange':'resize';
	var menu = document.getElementById('menu');
    function toggleHorizontal() {
		[].forEach.call(
	        document.getElementById('menu').querySelectorAll('.menu-can-transform'),
	        function(el){
	            el.classList.toggle('pure-menu-horizontal');
	        }
	    );
	};
	function toggleMenu() {
		var menu_item_count = $(".pure-menu-list li a:visible").size();
	    // set timeout so that the panel has a chance to roll up
	    // before the menu switches states
		if (menu.classList.contains('menu-open-'+menu_item_count)) {
	        setTimeout(toggleHorizontal, 500);
	    }
	    else {
	        toggleHorizontal();
	    }
	    menu.classList.toggle('menu-open-'+menu_item_count);
	    document.getElementById('toggle').classList.toggle('x');
	};
	function closeMenu() {
		var menu_item_count = $(".pure-menu-list li a:visible").size();
	    if (menu.classList.contains('menu-open-'+menu_item_count)) {
	        toggleMenu();
	    }
	}
	$("#toggle").click(function(){
	    toggleMenu();
	});
	window.addEventListener(WINDOW_CHANGE_EVENT, closeMenu);

	$(document).on("click", "form.ajax input[type='submit'], form.ajax button[type='submit']", function(){
  		active_ajax_form = $(this).parents('form');
		submit_ajax_form();
		return false;
	});

	$(".confirm").click(function(){
		if(confirm($(this).attr('data-confirm-message'))){
			return true;
		}else{
			return false;
		}
	});

	$(".notice").click(function(){
		$(".notice").slideUp(300, function(){
			$(".notice").remove();
		});
	});

	// Start all auto refreshers
	$.each($(".auto-refresh"), function(key, value){
		auto_refresh($(this));
	});

	$(".load_robots_for_select").change(function(){

		var type_id = $(this).attr('data-type-id');
		var type = $(this).attr('data-type');
		var mission_id = $("option:selected", this).attr('value');

		$.get("/robots_by_mission_load.php?mission_id="+mission_id+"&type="+type+"&type_id="+type_id, function(data) {
			$("select[name='robot_id']").html(data);
		});

	});

	$(".load_robots_for_select").trigger('change');

	$(".load_parameters_for_mission").change(function(){
		var mission_id = $("option:selected", this).attr('value');
		var url_params = "?mission_id=" + mission_id;

		var type = $(this).attr('data-type');
		var type_id = $(this).attr('data-type-id');

		if (type == 'order' && type_id != '0' && type_id!='undefined') {
			url_params += '&order_id=' + type_id;
		}
		else if (type == 'favorite' && type_id != '0' && type_id!='undefined') {
			url_params += '&favorite_id=' + type_id;
		}

		$.get("/mission_parameters_load.php" + url_params, function(data) {

			$("#mission-parameter-placeholder").html(data);
		});

	});

	$(".load_parameters_for_mission").trigger('change');

	map_update(true);
});

var auto_refresh_data = {};
function auto_refresh(obj){

	var refresh_rate = parseInt(obj.attr('data-refresh-rate'));
	var refresh_url = obj.attr('data-refresh-url');
	var force_update = obj.attr('data-refresh-force-update');

	if(auto_refresh_data[refresh_url] == undefined){
		auto_refresh_data[refresh_url] = null;
	}

	$.get(refresh_url, function(data) {

		// Only update the element if content has changed or updates should be forced
		if(force_update === 'true' || data != auto_refresh_data[refresh_url]){
			auto_refresh_data[refresh_url] = data;
			obj.html(data);
		}

		if(refresh_rate != 0){
			setTimeout(function(){auto_refresh(obj)}, refresh_rate);
		}
	});
}

// Map update function
var maps = {};
function map_update(recurring){

	if(recurring == undefined){
		recurring = true;
	}

	var map_url = '';

	$.each($(".mir-map:not(.upper-canvas)"), function(key, value){

		var track_type = $(this).attr('data-track-type');
		var track_id = $(this).attr('data-track-id');
		var initialized = $(this).attr('data-map-initialized');

		if(maps[track_type+"_"+track_id] == undefined || initialized == undefined){
			maps[track_type+"_"+track_id] = new MirMap($(this));
			$(this).attr('data-map-initialized', 'true'); // Made to detect if the map element has been overwritten by some ajax
		}

		map_url += '&track_type[]='+track_type+'&track_id[]='+track_id;

	});

	$.ajax({
		url: '/map_update.php?'+map_url,
		success: function(data) {
			eval(data); // Execute all the map update commands
		},
		complete: function(jqXHR, status){
			if(recurring == true){
				setTimeout(function(){map_update()}, 1000);
			}
		}
	});

}

// Ajax form functions
var submit_button_text = "";
var active_ajax_form;
var ajax_form_loading = false;
function submit_ajax_form(){

	if(ajax_form_loading){
		return false;
	}

	ajax_form_loading = true;

	// Clear all placeholders if browser does not support them
    if(!$.support.placeholder){
      	$.each( active_ajax_form.find('input, textarea'), function( key, value ) {
    		var placeholder_val = $(this).attr('placeholder');
    		if(placeholder_val == undefined){
    			return true;
    		}else{
    			if($(this).val() == placeholder_val){
    				$(this).val('');
    			}
    		}
    	});
    }

    var submit = $("button[type='submit']", active_ajax_form);
    submit_button_text = $(submit).html();
    submit.html("<i class=\"fa fa-spinner fa-spin fa-fw\"></i> "+$("#js_please_wait").text()+"\u2026");
	$(".form_error", active_ajax_form).slideUp(200);
	$(".form_message", active_ajax_form).slideUp(200);
	$("input, textarea, select").removeClass('error');

	$.ajax({
        type: active_ajax_form.attr('method'),
        data: active_ajax_form.serialize(),
        url: active_ajax_form.attr('action'),
        dataType: 'json',
        async: true,
        success: function(data) {
        	setTimeout(function(){
        		submit_ajax_form_success(data);
        	}, 200);
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
        	setTimeout(function(){
            	submit_ajax_form_error(XMLHttpRequest, textStatus, errorThrown);
			}, 200);
        },
        complete: function(jqXHR, status){
        	submit_ajax_form_complete(active_ajax_form);
        	ajax_form_loading = false;
        }
    });
	return false;
}

function submit_ajax_form_error(XMLHttpRequest, textStatus, errorThrown){
	$(".form_message", active_ajax_form).removeClass('success').addClass('error').html($("#js_an_error_occured_please_try_again").text()).slideDown(200);
	$("button[type='submit']", active_ajax_form).html(submit_button_text);
}

function submit_ajax_form_success(data){

	// Handle returned JSON data
    if(data.REDIRECT_URL != undefined){
    	if(data.REDIRECT_URL != ''){
    		window.location.href = data.REDIRECT_URL;
    	}else{
    		window.location.reload();
    	}
    }else{
    	$("button[type='submit']", active_ajax_form).html(submit_button_text);
		$("div.form_error", active_ajax_form).remove();
    }

    if(data.SUCCESS_MESSAGE != undefined || data.ERROR_MESSAGE != undefined){
		var message_elem = $(".form_message", active_ajax_form);
        if(data.SUCCESS_MESSAGE != undefined){
        	message_elem.removeClass('error').addClass('success').html(data.SUCCESS_MESSAGE).slideDown(200);
        }else{
        	message_elem.removeClass('success').addClass('error').html(data.ERROR_MESSAGE).slideDown(200);
        }
    }

    if(data.CALLBACK != undefined){
       	eval(data.CALLBACK);
    }

    if(data.CLEAR_FORM != undefined){
    	active_ajax_form.find('input').val('');
    	active_ajax_form.find('option[selected="selected"]').removeAttr('selected');
    	active_ajax_form.find('select option:first').attr('selected', 'selected');
    	active_ajax_form.find('textarea').val('');
    }

    if(data.ERRORS != undefined){
	function add_error( err_elem, error ) {
	    err_elem.addClass( 'error' );

	    if ( error != null ) {
		if ( err_elem.is( 'div' ) ) {
		    err_elem.text( error );
		} else {
	            err_elem.after( '<div class="form_error"></div>' );
	            err_elem.next().append( error );
	            $( ".form_error", active_ajax_form ).slideDown( 200 );
		}
	    }
	}

    	// Loop through all errors and mark field
        $.each(data.ERRORS, function(field_id, error){
			// Hide notice if errors
			$(".notice").slideUp(300, function(){
				$(".notice").remove();
			});
	    var err_elems = $("input[name='"+field_id+"'], select[name='"+field_id+"'], textarea[name='"+field_id+"']", active_ajax_form);

	    err_elems.each( function( i ) {
		if ( error !== null ) {
		    if ( error.index != null ) {
			if ( i == error.index ) {
			    add_error( $( this ), error );
			}
		    } else {
			add_error( $( this ), error );
		    }
		} else {
		    add_error( $( this ), error );
		}
	    });
        });
    }
}

function submit_ajax_form_complete(form){
	if(!$.support.placeholder){
		$.each(form.find('input[type="text"], textarea'), function(key, value) {
			var placeholder_val = $(this).attr('placeholder');
    		if(placeholder_val == undefined){
    			return true;
    		}else{
    			if($(this).val() == ""){
    				$(this).val(placeholder_val);
    			}
    		}
		});
	}
}

// Checks if element is within viewport, param must be jquery element
function is_element_in_view(elem, offset){

	if(offset == undefined){
		offset = 0;
	}

	var $window = $(window);

	var docViewTop = $window.scrollTop();
	var docViewBottom = docViewTop + $window.height();

	var elemTop = elem.offset().top + offset;
	var elemBottom = elemTop + elem.height();

	return((elemBottom <= docViewBottom) && (elemTop >= docViewTop));

}
