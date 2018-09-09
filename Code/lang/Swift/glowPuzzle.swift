//
//  main.swift
//  GlowPuzzle
//
//  Created by Jeff Fang on 5/6/16.
//  Copyright © 2016 swordx. All rights reserved.
//

//: Playground - noun: a place where people can play

import Foundation

var str = "Hello, playground"
print(str)

enum cellStatus {
    case r,b,x
    func c() -> Character {
        switch self {
        case r:
            return "0"
        case b:
            return "1"
        case x:
            return "X"
        }
    }
}

enum direction {
    case up, down, left, right
    func c() -> Character{
        switch self {
        case up:
            return "U"
        case down:
            return "D"
        case left:
            return "L"
        case right:
            return "R"
        }
    }
}

struct Map {
    var cellsInfo: [[cellStatus]]
    var whiteCellPosition: (Int, Int)
    var routine: String = ""
    var level = 0
    var mapID: String
    
    init(withTable: [[cellStatus]]) {
        cellsInfo = withTable
        var position = (4, 4)
        var mapid = ""
        for i in 0...3 {
            for j in 0...3{
                if withTable[i][j] == .x {
                    position = (i, j)
                }
                mapid.append(withTable[i][j].c())
            }
        }
        whiteCellPosition = position
        mapID = mapid
    }
}

extension Map: Equatable {}
func ==(lhs: Map, rhs: Map) -> Bool {
    for i in 0...3 {
        for j in 0...3 {
            if lhs.cellsInfo[i][j] != rhs.cellsInfo[i][j] {
                return false
            }
        }
    }
    return true
}

let start = Map(withTable: [[.x, .r, .b, .b],
    [.r, .r, .b, .b],
    [.r, .r, .b, .b],
    [.r, .r, .b, .b]])

let start2 = Map(withTable: [[.r, .r, .b, .b],
    [.r, .b, .b, .b],
    [.r, .x, .r, .b],
    [.r, .r, .b, .b]])

let finish = Map(withTable: [[.x, .b, .r, .b],
    [.b, .r, .b, .r],
    [.r, .b, .r, .b],
    [.b, .r, .b, .r]])

let testMap = Map(withTable: [[.r, .b, .r, .b],
    [.b, .r, .b, .r],
    [.r, .b, .r, .b],
    [.b, .r, .b, .x]])

let testMap2 = Map(withTable: [[.r, .b, .r, .b],
    [.b, .r, .b, .r],
    [.r, .b, .r, .b],
    [.b, .r, .b, .x]])



if testMap == testMap2 {
    print("Equatable done!")
}

func printMap(map: Map) {
    for i in 0...3 {
        for j in 0...3 {
            print("\(map.cellsInfo[i][j].c()) ",terminator: "")
        }
        print("")
    }
    print("")
}

func move(map: Map, toTarget target:(Int, Int)) -> Map {
    let wPst = map.whiteCellPosition
    var cellsInfo = map.cellsInfo
    cellsInfo[wPst.0][wPst.1] = cellsInfo[target.0][target.1]
    cellsInfo[target.0][target.1] = .x
    let newMap = Map(withTable: cellsInfo)
    return newMap
}

func moveWhiteCellIn(map: Map, to: direction) -> Map {
    switch to {
    case .up:
        if map.whiteCellPosition.0 != 0 {
            let targetPst = (map.whiteCellPosition.0 - 1, map.whiteCellPosition.1)
            return move(map, toTarget: targetPst)
        } else {
            return map
        }
    case .down:
        if map.whiteCellPosition.0 != 3 {
            let targetPst = (map.whiteCellPosition.0 + 1, map.whiteCellPosition.1)
            return move(map, toTarget: targetPst)
        } else {
            return map
        }
    case .right:
        if map.whiteCellPosition.1 != 3 {
            let targetPst = (map.whiteCellPosition.0, map.whiteCellPosition.1 + 1)
            return move(map, toTarget: targetPst)
        } else {
            return map
        }
    case .left:
        if map.whiteCellPosition.1 != 0 {
            let targetPst = (map.whiteCellPosition.0, map.whiteCellPosition.1 - 1)
            return move(map, toTarget: targetPst)
        } else {
            return map
        }
    }
}

print(testMap.whiteCellPosition)
testMap.cellsInfo
moveWhiteCellIn(testMap, to: .left).cellsInfo

func mapsEqual (a: Map, b: Map) -> Bool {
    for i in 0...3 {
        for j in 0...3 {
            if a.cellsInfo[i][j] != b.cellsInfo[i][j] {
                return false
            }
        }
    }
    return true
}

func findRoutine(fromMap start:Map, toMap end:Map, via:String) {
        
}

var mapIDLog: [String] = []
var opQ: [Map] = [start]
var found = false
var rowNum = 1

func solver() {
    while !found {
        testQ()
    }
    print("*** found!")
}

func testQ() {
    let map = opQ[0]
    //    print("*** evel = \(map.level)")
    print("\(rowNum++) - \(map.routine)")
    
    //    printMap(map)
    if mapIDLog.contains(map.mapID) {
        //        print("*** contains!")
        opQ.removeAtIndex(0)
        testQ()
        return
    }
    
    mapIDLog.append(map.mapID)
    
    if map == finish {
        print("Found the Answer!")
        print(map.routine)
        found = true
        return
    }
    
    appendQForMap(map, withRoute: map.routine)
    opQ.removeAtIndex(0)
    //    print("********** Exit Level \(map.level) **********")
}

func appendQForMap(map: Map, withRoute route: String) {
//    print ("appendQForMap \(route)")
//    printMap(map)
    
    var stepR = moveWhiteCellIn(map, to: .right)
    var stepD = moveWhiteCellIn(map, to: .down)
    var stepL = moveWhiteCellIn(map, to: .left)
    var stepU = moveWhiteCellIn(map, to: .up)
    
    stepR.level += 1
    stepD.level += 1
    stepL.level += 1
    stepU.level += 1
    
    if stepL != map {
        stepL.routine = route
        stepL.routine.append(direction.left.c())
        opQ.append(stepL)
    }
    
    if stepU != map {
        stepU.routine = route
        stepU.routine.append(direction.up.c())
        opQ.append(stepU)
    }
    
    if stepR != map {
        stepR.routine = route
        stepR.routine.append(direction.right.c())
//        print("***stepR.routine = \(stepR.routine)")
        opQ.append(stepR)
    }
    
    if stepD != map {
        stepD.routine = route
        stepD.routine.append(direction.down.c())
        opQ.append(stepD)
    }
}

//testQ()
solver()

//var testhhhh:NSString = "tes"
//if testhhhh.length >= 3 {
//    print("good!")
//}

//func testAppend() {
//    var string: NSString = ""
//    let char:Character = "a"
//    string().append(char) as
//    print(string)
//    answers.append(string as String)
//}

//print("---------- Answer List ----------")
//print(findRoutine(fromMap: start, toMap: finish, via: ""))

//testAppend()
//print(answers)


//print(testMap == testMap2)


