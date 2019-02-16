package com.scalarmi

//Serializable class whose remote object is being passed

@SerialVersionUID(100L)
class Point(val xc: Int, val yc: Int) extends Serializable {
  var x: Int = xc
  var y: Int = yc

  //class methods
  def move(dx: Int, dy: Int) {
    x = x + dx
    y = y + dy
    println("Point x location : " + x)
    println("Point y location : " + y)
  }
}
