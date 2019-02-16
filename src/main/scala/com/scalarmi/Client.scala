package com.scalarmi

import java.rmi.registry.LocateRegistry

/*
 * Proof of concept of the Referential Integrity property in Java RMI framework
 *
 * @author Tanveer Shaikh
 */

//main-class
object Client extends App {
  val clientURL = s"rmi://127.0.0.1:1234/test"
  val registry = LocateRegistry.getRegistry(1234)
  val server = registry.lookup(clientURL).asInstanceOf[ServerInterface]

  val point1 = new Point(10, 20)
  point1.move(2, 4)
  val point2 = point1

  //The remote object is passed by reference, not by copying the actual remote implementation.
  server.inspectObject(point1, point2)
}
