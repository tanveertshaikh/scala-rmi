package com.scalarmi

import java.rmi.{ Naming, RemoteException }
import java.rmi.registry.LocateRegistry
import java.rmi.server.UnicastRemoteObject

/*
 * Proof of concept of the Referential Integrity property in Java RMI framework
 *
 * @author Tanveer Shaikh
 */

//main-class
object Server extends App with ServerInterface {
  val serverURL = s"rmi://127.0.0.1:1234/test"
  val stub = UnicastRemoteObject.exportObject(this, 1234)
  val registry = LocateRegistry.createRegistry(1234)
  registry.rebind(serverURL, stub)

  //The remote method being invoked
  def inspectObject(object1: Point, object2: Point): Unit = {

    println(s"Object 1: ${object1.hashCode}, Object 2: ${object2.hashCode}")
    println(s"Object 1 X-coordinate: ${object1.x.hashCode}, Object 2 X-coordinate: ${object2.x.hashCode}")
    println(s"Object 1 Y-coordinate: ${object1.y.hashCode}, Object 2 Y-coordinate: ${object2.y.hashCode}")

    //Local function defined inside the class of remote object
    object1.move(5, 10)

    //We can observe the same changes in fields of both the objects even when only the first object is modified
    println(s"Object 1: ${object1.hashCode}, Object 2: ${object2.hashCode}")
    println(s"Object 1 X-coordinate: ${object1.x.hashCode}, Object 2 X-coordinate: ${object2.x.hashCode}")
    println(s"Object 1 Y-coordinate: ${object1.y.hashCode}, Object 2 Y-coordinate: ${object2.y.hashCode}")

    if (object1 == object2 && object1.x == object2.x && object1.y == object2.y) {
      println("The references of these objects are equal!")
      println("Hence, Referential Integrity is maintained")
    }
  }
}
