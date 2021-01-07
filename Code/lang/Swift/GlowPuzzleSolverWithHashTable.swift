//
//  main.swift
//  GlowPuzzle
//
//  Created by Jeff Fang on 5/7/16.
//  Copyright © 2016 swordx. All rights reserved.
//

//: Playground - noun: a place where people can play

import Foundation

// MARK: - Data Structure

enum cellStatus {
    case r,b,x
    func c() -> Character {
        switch self {
        case .r:
            return "0"
        case .b:
            return "1"
        case .x:
            return "X"
        }
    }
}

enum direction {
    case up, down, left, right
    func c() -> Character{
        switch self {
        case .up:
            return "U"
        case .down:
            return "D"
        case .left:
            return "L"
        case .right:
            return "R"
        }
    }
}

struct Map {
    var cellsInfo: [[cellStatus]]
    var whiteCellPosition: (Int, Int)
    var routine: String = ""
    var mapID: Int
    
    init(withTable: [[cellStatus]]) {
        cellsInfo = withTable
        var position = (4, 4)
        var mapString = ""
        for i in 0...3 {
            for j in 0...3{
                if withTable[i][j] == .x {
                    position = (i, j)
                }
                mapString.append(withTable[i][j].c())
            }
        }
        whiteCellPosition = position
        
        let range: Range<String.Index> = mapString.range(of: "X")!
        mapString.remove(at: range.lowerBound)
        
        let wCellHash: Int = position.0 * 4 + position.1
        let tbHash = Int(mapString, radix: 2)
        let wCellHashBinStr = String(wCellHash, radix:2)
        let tbHashBinStr = String(tbHash!, radix:2)
        let mapHashBinStr = wCellHashBinStr + tbHashBinStr
        
        mapID = Int(mapHashBinStr, radix: 2)!

    }
}

//func printMap(map: Map) {
//    for i in 0...3 {
//        for j in 0...3 {
//            print("\(map.cellsInfo[i][j].c()) ",terminator: "")
//        }
//        print("")
//    }
//    print("")
//}


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

// MARK: - Data Input

let start = Map(withTable: [[.x, .r, .b, .b],
    [.r, .r, .b, .b],
    [.r, .r, .b, .b],
    [.r, .r, .b, .b]])

let finish = Map(withTable: [[.x, .b, .r, .b],
    [.b, .r, .b, .r],
    [.r, .b, .r, .b],
    [.b, .r, .b, .r]])

// MARK: - Functions

func moveWhiteCellIn(map: Map, to: direction) -> Map {
    switch to {
    case .up:
        if map.whiteCellPosition.0 != 0 {
            let targetPst = (map.whiteCellPosition.0 - 1, map.whiteCellPosition.1)
            return move(map: map, toTargetPosition: targetPst)
        } else {
            return map
        }
    case .down:
        if map.whiteCellPosition.0 != 3 {
            let targetPst = (map.whiteCellPosition.0 + 1, map.whiteCellPosition.1)
            return move(map: map, toTargetPosition: targetPst)
        } else {
            return map
        }
    case .right:
        if map.whiteCellPosition.1 != 3 {
            let targetPst = (map.whiteCellPosition.0, map.whiteCellPosition.1 + 1)
            return move(map: map, toTargetPosition: targetPst)
        } else {
            return map
        }
    case .left:
        if map.whiteCellPosition.1 != 0 {
            let targetPst = (map.whiteCellPosition.0, map.whiteCellPosition.1 - 1)
            return move(map: map, toTargetPosition: targetPst)
        } else {
            return map
        }
    }
}

func move(map: Map, toTargetPosition target:(Int, Int)) -> Map {
    let wPst = map.whiteCellPosition
    var cellsInfo = map.cellsInfo
    cellsInfo[wPst.0][wPst.1] = cellsInfo[target.0][target.1]
    cellsInfo[target.0][target.1] = .x
    let newMap = Map(withTable: cellsInfo)
    return newMap
}

// MARK: - MainLogic

// 2^19 = 524288, the largest hash number
var mapHashTable = [Bool](repeating: false, count:524288)


var opQ: [Map] = [start]
var found = false
var rowNum = 0

func solver() {
    while !found {
        testQ()
    }
    print("*** found!")
}

func testQ() {
    let map = opQ[0]

    rowNum += 1
    print("\(rowNum) - \(map.routine)")
    
    if mapHashTable[map.mapID] {
        opQ.remove(at: 0)
        return
    }
    
    mapHashTable[map.mapID] = true
    
    if map == finish {
        print("Found the Answer!")
        print(map.routine)
        found = true
        return
    }
    
    appendNext2QForMap(map: map, withRoute: map.routine)
    opQ.remove(at: 0)
}

func appendNext2QForMap(map: Map, withRoute route: String) {
    var stepR = moveWhiteCellIn(map: map, to: .right)
    var stepD = moveWhiteCellIn(map: map, to: .down)
    var stepL = moveWhiteCellIn(map: map, to: .left)
    var stepU = moveWhiteCellIn(map: map, to: .up)
    
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
        opQ.append(stepR)
    }
    
    if stepD != map {
        stepD.routine = route
        stepD.routine.append(direction.down.c())
        opQ.append(stepD)
    }
}


solver()





