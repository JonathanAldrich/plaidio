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

import java.lang.reflect.Array;

public class JavaArray<T>
{
    T[] p_Array;

    @SuppressWarnings("unchecked")
	public JavaArray(T sample, int size)
    {
        p_Array = (T[]) Array.newInstance(sample.getClass(), size);
    }

    public int length()
    {
        return p_Array.length;
    }
    
    public void set(int index, T item)
    {
        p_Array[index] = item;
    }
    
	public void setArray(T[] array)
    {
        p_Array = array;
    }

    public T get(int index)
    {
        return p_Array[index];
    }

    public T[] getArray()
    {
        return p_Array;
    }
}

