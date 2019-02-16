package com.scalarmi

import java.rmi.registry.LocateRegistry
import java.rmi.server.UnicastRemoteObject

/*
 * Proof of concept of the Referential Integrity property in Java RMI framework
 *
 * @author Tanveer Shaikh
 */

//main-class
object RMIServer extends App with RMIServerInterface {

  //Server URL created using rmi://localhost:port/service
  val serverURL = s"rmi://127.0.0.1:1234/test"

  //Unicast Remote Object Stub is created and the object is exported to Client through the port number mentioned
  val port = 1234
  val stub = UnicastRemoteObject.exportObject(this, port)

  //RMI registry is instantiated
  val registry = LocateRegistry.createRegistry(port)

  //URL is being passed instead of string tag identifier while binding the stub containing the server object to the registry
  registry.rebind(serverURL, stub)

  //The remote method being invoked
  def inspectObject(object1: Point, object2: Point): Point = {

    //Printing object reference as well as values of object fields
    println(s"Object 1: ${object1.hashCode}, Object 2: ${object2.hashCode}")
    println(s"Object 1 X-coordinate: ${object1.x.hashCode}, Object 2 X-coordinate: ${object2.x.hashCode}")
    println(s"Object 1 Y-coordinate: ${object1.y.hashCode}, Object 2 Y-coordinate: ${object2.y.hashCode}")

    //Local function defined inside the class of remote object which changes the fields of one object
    object1.move(5, 10)

    //We can observe the same changes in fields of both the objects even when only the first object is modified
    println(s"Object 1: ${object1.hashCode}, Object 2: ${object2.hashCode}")
    println(s"Object 1 X-coordinate: ${object1.x.hashCode}, Object 2 X-coordinate: ${object2.x.hashCode}")
    println(s"Object 1 Y-coordinate: ${object1.y.hashCode}, Object 2 Y-coordinate: ${object2.y.hashCode}")

    //Conditions to check RMI Referential Integrity
    if (object1 == object2 && object1.x == object2.x && object1.y == object2.y) {
      println("The references of these objects are equal!")
      println("Hence, Referential Integrity is maintained")
    }
    object1
  }
}
