/*************************************************************************
 * Copyright 2009-2012 Eucalyptus Systems, Inc.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; version 3 of the License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see http://www.gnu.org/licenses/.
 *
 * Please contact Eucalyptus Systems, Inc., 6755 Hollister Ave., Goleta
 * CA 93117, USA or visit http://www.eucalyptus.com/licenses/ if you need
 * additional information or have any questions.
 ************************************************************************/
package com.eucalyptus.crypto;

import java.security.GeneralSecurityException;
import javax.crypto.Cipher;

public enum Ciphers {

  AES_GCM( "AES/GCM/NoPadding", "BC" ),
  AES_CBC("AES/CBC/NoPadding", "BC"),
  RSA_PKCS1( "RSA/ECB/PKCS1Padding" );  //"None" is more correct than ECB, but ECB is the required algorithm for JDKs

  public Cipher get( )  throws GeneralSecurityException {
    return provider.isEmpty() ?
        Cipher.getInstance( transformation ) :
        Cipher.getInstance( transformation, provider );
  }

  private final String transformation;
  private final String provider;

  private Ciphers( final String transformation ) {
    this( transformation, "" );
  }

  private Ciphers( final String transformation,
                   final String defaultProvider ) {
    this.transformation = transformation;
    this.provider = System.getProperty( "euca.crypto.cipher.provider." + transformation, defaultProvider );
  }
}
