package com.scalarmi

import java.rmi.{Remote, RemoteException}

//Remote trait which acts as an interface to the server

trait RMIServerInterface extends Remote {
  @throws(classOf[RemoteException])
  def inspectObject(object1: Point, object2: Point): Point
}
