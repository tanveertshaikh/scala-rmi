package com.scalarmi

import java.rmi.registry.LocateRegistry
import java.rmi.server.UnicastRemoteObject

object Server extends App with ServerInterface {
  val serverURL = s"rmi://127.0.0.1:1234/test"
  val stub = UnicastRemoteObject.exportObject(this, 1234)
  val registry = LocateRegistry.createRegistry(1234)
  registry.rebind(serverURL, stub)

  def inspectObject(object1: Point, object2: Point): Unit = {
    println(s"Object 1: ${object1.hashCode}, obj2: ${object2.hashCode}")
    if (object1 == object2) {
      println("The references of these objects are equal!")
      println("Hence, Referential Integrity is maintained")
    }
  }
}
