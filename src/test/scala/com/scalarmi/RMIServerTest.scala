package com.scalarmi

import java.rmi.registry.LocateRegistry

import org.junit.Assert._
import org.junit.{Before, Test}
import org.scalatest.junit.JUnitSuite

//JUnit test cases for server

class RMIServerTest extends JUnitSuite with Serializable {

  @Before
  def initialize(): Unit = {
    //Static variables initialized
    /*
    val registry = LocateRegistry.getRegistry(1234)

    val boundNames: Array[String] = registry.list()

    boundNames.foreach(name =>
      if (name.equals("rmi://127.0.0.1:1234/test")) {
        registry.unbind("rmi://127.0.0.1:1234/test")
      }
    )
    val port = 1234
    val stub = UnicastRemoteObject.exportObject(RMIServer, port)
    registry.bind("rmi://127.0.0.1:1234/test", RMIServer)
    */
  }

  @Test
  def verifyObjectCreated(): Unit = {
    assertEquals(RMIServer.getClass.toString, "class com.scalarmi.RMIServer$")
  }

  @Test
  def verifyStaticVariables(): Unit = {
    val serverURL: Option[String] = null
    val port = 0
    assertEquals(RMIServer.serverURL, serverURL)
    assertEquals(RMIServer.port, port)
  }

  @Test
  def verifyRegistryIsEmpty(): Unit = {
    val port = 1234
    val registry = LocateRegistry.getRegistry(port)
    val boundNames: Array[String] = registry.list()

    boundNames.foreach(name =>
      if (name.equals("rmi://127.0.0.1:1234/test")) {
        registry.unbind("rmi://127.0.0.1:1234/test")
      }
    )
    assert(registry.list().isEmpty)
  }

  @Test
  def verifyInspectObject(): Unit = {

    //Tested inspectObject() method locally on the server

    //Instance of class Point is created
    val point1 = new Point(18, 24)
    point1.move(2, 5)
    val point2 = point1
    val returnedPoint = RMIServer.inspectObject(point1, point2)

    println("Point x location : " + returnedPoint.x)
    println("Point y location : " + returnedPoint.y)

    assert(point1 == point2)
    assert(point1.x == point2.x)
    assert(point1.y == point2.y)

    assert(returnedPoint.x === 25)
    assert(returnedPoint.y === 39)
  }
}
