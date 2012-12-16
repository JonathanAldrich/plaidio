/**
 *  Copyright (c) 2012 The PLAIDIO project
 * 
 *  This file is part of PLAIDIO, I/O Library for Plaid Language.
 *
 *  This library is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 * 
 *  This library is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without ven the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with Plaid Programming Language. If not, see <www.gnu.org/licenses>.
 */

package com.akefirad.plaid;

public class ByteArray
{
    byte[] p_Bytes;

	public ByteArray(int size)
    {
		p_Bytes = new byte[size];
    }

    public int length()
    {
        return p_Bytes.length;
    }
    
    public void set(int index, byte item)
    {
    	p_Bytes[index] = item;
    }
    
	public void setBytes(byte[] bytes)
    {
		p_Bytes = bytes;
    }

    public byte get(int index)
    {
        return p_Bytes[index];
    }

    public byte[] getBytes()
    {
        return p_Bytes;
    }
}

