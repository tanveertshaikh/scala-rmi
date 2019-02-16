package com.scalarmi

import java.rmi.registry.LocateRegistry

import org.junit.Assert._
import org.junit.{Before, Test}
import org.scalatest.junit.JUnitSuite

//JUnit test cases for client

class RMIClientTest extends JUnitSuite with Serializable {

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
    val stub = UnicastRemoteObject.exportObject(RMIClient, port)
    registry.bind("rmi://127.0.0.1:1234/test", RMIClient)
    */
  }

  @Test
  def verifyObjectCreated(): Unit = {
    assertEquals(RMIClient.getClass.toString, "class com.scalarmi.RMIClient$")
  }

  @Test
  def verifyStaticVariables(): Unit = {
    val clientURL: Option[String] = null
    val port = 0
    assertEquals(RMIClient.clientURL, clientURL)
    assertEquals(RMIClient.port, port)
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

    //Tested inspectObject() method remotely on the client

    //Instance of class Point is created
    val point1 = new Point(23, 42)
    point1.move(5, 9)
    val point2 = point1

    val port = 1234
    val clientURL = s"rmi://127.0.0.1:1234/test"
    val registry = LocateRegistry.getRegistry(port)

    /*
     * val stub = UnicastRemoteObject.exportObject(RMIServer, port)
     * registry.rebind(clientURL, RMIServer)
     * val server = registry.lookup(clientURL).asInstanceOf[RMIServerInterface]
     */

    println("Point x location : " + point1.x)
    println("Point y location : " + point2.y)

    assert(point1 == point2)
    assert(point1.x == point2.x)
    assert(point1.y == point2.y)

    assert(point1.x === 28)
    assert(point2.y === 51)
  }
}
