/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.msgpack.utils;

/**
 *
 * @author Aleksey.Shulga
 */
public final class ByteOrder {
     public static final ByteOrder BIG_ENDIAN = new ByteOrder();
 
     public static final ByteOrder LITTLE_ENDIAN = new ByteOrder();
 
     public static ByteOrder nativeOrder() {
         throw new UnsupportedOperationException  ();
     }
 
 }  
 


