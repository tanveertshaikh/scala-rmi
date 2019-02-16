package com.scalarmi

import java.rmi.{Remote, RemoteException}

trait ServerInterface extends Remote {
  @throws(classOf[RemoteException])
  def inspectObject(object1: Point, object2: Point): Unit
}
