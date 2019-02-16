package com.scalarmi

import java.rmi.registry.LocateRegistry

/*
 * Proof of concept of the Referential Integrity property in Java RMI framework
 *
 * @author Tanveer Shaikh
 */

//main-class
object RMIClient extends App {

  //Client URL created using rmi://localhost:port/service
  val clientURL = s"rmi://127.0.0.1:1234/test"

  /*
   * Client connects to the server using the default port number in order to bind to server object.
   * getRegistry(port) returns the local registry instance
   */
  val port = 1234
  val registry = LocateRegistry.getRegistry(port)

  /*
   * Client searches for server object reference in RMI registry
   * Therefore, this lookup should always be done after the server is running
   */
  val server = registry.lookup(clientURL).asInstanceOf[RMIServerInterface]

  val point1 = new Point(10, 20)
  point1.move(2, 4)
  val point2 = point1

  /*
   * Remote method is invoked - inspectObject on Server object
   * The remote object is passed by reference, not by copying the actual remote implementation
   */
  val returnedPoint = server.inspectObject(point1, point2)
  println("Point x location : " + returnedPoint.x)
  println("Point y location : " + returnedPoint.y)
}
