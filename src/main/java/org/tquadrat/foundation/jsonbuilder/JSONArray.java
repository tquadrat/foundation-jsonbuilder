/*
 * ============================================================================
 *  Copyright © 2002-2026 by Thomas Thrien.
 *  All Rights Reserved.
 * ============================================================================
 *  Licensed to the public under the agreements of the GNU Lesser General Public
 *  License, version 3.0 (the "License"). You may obtain a copy of the License at
 *
 *       http://www.gnu.org/licenses/lgpl.html
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 *  WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 *  License for the specific language governing permissions and limitations
 *  under the License.
 */

package org.tquadrat.foundation.jsonbuilder;

import org.apiguardian.api.API;
import org.tquadrat.foundation.annotation.ClassVersion;
import org.tquadrat.foundation.jsonbuilder.internal.JSONArrayImpl;
import org.tquadrat.foundation.lang.value.Dimension;
import org.tquadrat.foundation.lang.value.DimensionedValue;

import java.math.BigDecimal;
import java.math.BigInteger;

import static org.apiguardian.api.API.Status.STABLE;
import static org.tquadrat.foundation.jsonbuilder.JSONLiteral.FALSE;
import static org.tquadrat.foundation.jsonbuilder.JSONLiteral.TRUE;

/**
 *  <p>{@summary The definition of a JSON array.}</p>
 *  <p>Basically, aa JSON array is dense, meaning that an array with the
 *  maximum index <i><code>i</code></i> has exactly {@code i + 1} elements –
 *  although some of these elements could be
 *  {@link JSONLiteral#NULL null}.</p>
 *  <p>The {@code add(T)} methods will add the given value to the end of the
 *  array, while the {@code add(int,T)} methods will insert the given value at
 *  the position identified by the given {@code index}; the already existing
 *  values at that position and above will be moved to the next higher
 *  position. If the {@code index} is equal to the
 *  {@linkplain #size() size}
 *  of the array, the value will be added to the end of the array. That means
 *  that {@code add( value )} and {@code add( size(), value )} are equivalent.
 *  A value for {@code index} that is greater than the size of the array (or
 *  less than 0) will cause an
 *  {@link IndexOutOfBoundsException}
 *  to be thrown.</p>
 *  <p>The {@code set(int,T)} methods will replace the value at the given
 *  {@code index}; this means, that after a call to
 *  {@link #set(int,JSONValue)}
 *  the
 *  {@linkplain #size() size}
 *  of this array remains the same, while a call to
 *  {@link #add(JSONValue)}
 *  or
 *  {@link #add(int,JSONValue)}
 *  increases the array size by one.</p>
 *  <p>Obviously must the value for the {@code index} argument of a
 *  {@code set()} match an existing array position; otherwise an
 *  {@link IndexOutOfBoundsException}
 *  is thrown.</p>
 *  <p>An instance of {@code JSONArray} is not thread-safe.</p>
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: JSONArray.java 1195 2026-04-15 21:33:40Z tquadrat $
 *  @since 0.25.0
 *
 *  @UMLGraph.link
 */
@SuppressWarnings( "ClassWithTooManyMethods" )
@ClassVersion( sourceVersion = "$Id: JSONArray.java 1195 2026-04-15 21:33:40Z tquadrat $" )
@API( status = STABLE, since = "0.25.0" )
public sealed interface JSONArray extends Iterable<JSONValue>, JSONValue
    permits JSONArrayImpl
{
        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  <p>{@summary Appends the given value as a new element to the end of
     *  this array.}</p>
     *
     *  @param  value   The value to add.
     */
    public void add( final BigDecimal value );

    /**
     *  <p>{@summary Appends the given value as a new element to the end of
     *  this array.}</p>
     *
     *  @param  value   The value to add.
     */
    public void add( final BigInteger value );

    /**
     *  <p>{@summary Appends the given value as a new element to the end of
     *  this array.}</p>
     *
     *  @param  value   The value to add.
     */
    public default void add( final boolean value )
    {
        add( value ? TRUE : FALSE );
    }   //  add()

    /**
     *  <p>{@summary Appends the given value as a new element to the end of
     *  this array.}</p>
     *
     *  @param  value   The value to add.
     */
    public void add( final double value );

    /**
     *  <p>{@summary Appends the given value as a new element to the end of
     *  this array.}</p>
     *
     *  @param  <T> The type of the dimension for the value.
     *  @param  value   The value.
     *  @param  targetUnit  The dimension for the output.
     *
     *  @see JSONBuilder#valueOf(DimensionedValue,Dimension)
     */
    public <T extends Dimension> void add( final DimensionedValue<T> value, final T targetUnit );

    /**
     *  <p>{@summary Appends the given value as a new element to the end of
     *  this array.}</p>
     *
     *  @param  value   The value to add.
     */
    public void add( final Double value );

    /**
     *  <p>{@summary Appends the given value as a new element to the end of
     *  this array.}</p>
     *
     *  @param  value   The value to add.
     */
    public void add( final float value );

    /**
     *  <p>{@summary Appends the given value as a new element to the end of
     *  this array.}</p>
     *
     *  @param  value   The value to add.
     */
    public void add( final Float value );

    /**
     *  <p>{@summary Appends the given value as a new element to the end of
     *  this array.}</p>
     *
     *  @param  value   The value to add.
     */
    public void add( final int value );

    /**
     *  <p>{@summary Appends the given value as a new element to the end of
     *  this array.}</p>
     *
     *  @param  value   The value to add.
     */
    public void add( final Integer value );

    /**
     *  <p>{@summary Appends the given value as a new element to the end of
     *  this array.}</p>
     *
     *  @param  value   The value to add.
     */
    public void add( final JSONValue value );

    /**
     *  <p>{@summary Appends the given value as a new element to the end of
     *  this array.}</p>
     *
     *  @param  value   The value to add.
     */
    public void add( final long value );

    /**
     *  <p>{@summary Appends the given value as a new element to the end of
     *  this array.}</p>
     *
     *  @param  value   The value to add.
     */
    public void add( final Long value );

    /**
     *  <p>{@summary Appends the given value as a new element to the end of
     *  this array.}</p>
     *
     *  @param  value   The value to add.
     */
    public void add( final String value );

    /**
     *  <p>{@summary Inserts the given value as a new element to the given
     *  postion  this array and moves the other elements to a by one higher
     *  index.}</p>
     *  <p>If {@code index} is equals to the
     *  {@linkplain #size() size}
     *  of the array, the element is appended to the end of the array.</p>
     *
     *  @param  index   The index.
     *  @param  value   The value to add.
     *  @throws IndexOutOfBoundsException   The index is less than 0 or greater
     *      than the
     *      {@linkplain #size() size}
     *      of the array.
     */
    public void add( final int index, final BigDecimal value ) throws IndexOutOfBoundsException;

    /**
     *  <p>{@summary Inserts the given value as a new element to the given
     *  postion  this array and moves the other elements to a by one higher
     *  index.}</p>
     *  <p>If {@code index} is equals to the
     *  {@linkplain #size() size}
     *  of the array, the element is appended to the end of the array.</p>
     *
     *  @param  index   The index.
     *  @param  value   The value to add.
     *  @throws IndexOutOfBoundsException   The index is less than 0 or greater
     *      than the
     *      {@linkplain #size() size}
     *      of the array.
     */
    public void add( final int index, final BigInteger value ) throws IndexOutOfBoundsException;

    /**
     *  <p>{@summary Inserts the given value as a new element to the given
     *  postion  this array and moves the other elements to a by one higher
     *  index.}</p>
     *  <p>If {@code index} is equals to the
     *  {@linkplain #size() size}
     *  of the array, the element is appended to the end of the array.</p>
     *
     *  @param  index   The index.
     *  @param  value   The value to add.
     *  @throws IndexOutOfBoundsException   The index is less than 0 or greater
     *      than the
     *      {@linkplain #size() size}
     *      of the array.
     */
    public default void add( final int index, final boolean value ) throws IndexOutOfBoundsException
    {
        add( index, value ? TRUE : FALSE );
    }   //  add()

    /**
     *  <p>{@summary Inserts the given value as a new element to the given
     *  postion of this array and moves the other elements to a by one higher
     *  index.}</p>
     *  <p>If {@code index} is equals to the
     *  {@linkplain #size() size}
     *  of the array, the element is appended to the end of the array.</p>
     *
     *  @param  <T> The type of the dimension for the value.
     *  @param  index   The index.
     *  @param  value   The value.
     *  @param  targetUnit  The dimension for the output.
     *  @throws IndexOutOfBoundsException   The index is less than 0 or greater
     *      than the
     *      {@linkplain #size() size}
     *      of the array.
     *
     *  @see JSONBuilder#valueOf(DimensionedValue,Dimension)
     */
    public <T extends Dimension> void add( final int index, final DimensionedValue<T> value, final T targetUnit ) throws IndexOutOfBoundsException;

    /**
     *  <p>{@summary Inserts the given value as a new element to the given
     *  postion of this array and moves the other elements to a by one higher
     *  index.}</p>
     *  <p>If {@code index} is equals to the
     *  {@linkplain #size() size}
     *  of the array, the element is appended to the end of the array.</p>
     *
     *  @param  index   The index.
     *  @param  value   The value to add.
     *  @throws IndexOutOfBoundsException   The index is less than 0 or greater
     *      than the
     *      {@linkplain #size() size}
     *      of the array.
     */
    public void add( final int index, final double value ) throws IndexOutOfBoundsException;

    /**
     *  <p>{@summary Inserts the given value as a new element to the given
     *  postion  this array and moves the other elements to a by one higher
     *  index.}</p>
     *  <p>If {@code index} is equals to the
     *  {@linkplain #size() size}
     *  of the array, the element is appended to the end of the array.</p>
     *
     *  @param  index   The index.
     *  @param  value   The value to add.
     *  @throws IndexOutOfBoundsException   The index is less than 0 or greater
     *      than the
     *      {@linkplain #size() size}
     *      of the array.
     */
    public void add( final int index, final Double value ) throws IndexOutOfBoundsException;

    /**
     *  <p>{@summary Inserts the given value as a new element to the given
     *  postion  this array and moves the other elements to a by one higher
     *  index.}</p>
     *  <p>If {@code index} is equals to the
     *  {@linkplain #size() size}
     *  of the array, the element is appended to the end of the array.</p>
     *
     *  @param  index   The index.
     *  @param  value   The value to add.
     *  @throws IndexOutOfBoundsException   The index is less than 0 or greater
     *      than the
     *      {@linkplain #size() size}
     *      of the array.
     */
    public void add( final int index, final float value ) throws IndexOutOfBoundsException;

    /**
     *  <p>{@summary Inserts the given value as a new element to the given
     *  postion  this array and moves the other elements to a by one higher
     *  index.}</p>
     *  <p>If {@code index} is equals to the
     *  {@linkplain #size() size}
     *  of the array, the element is appended to the end of the array.</p>
     *
     *  @param  index   The index.
     *  @param  value   The value to add.
     *  @throws IndexOutOfBoundsException   The index is less than 0 or greater
     *      than the
     *      {@linkplain #size() size}
     *      of the array.
     */
    public void add( final int index, final Float value ) throws IndexOutOfBoundsException;

    /**
     *  <p>{@summary Inserts the given value as a new element to the given
     *  postion  this array and moves the other elements to a by one higher
     *  index.}</p>
     *  <p>If {@code index} is equals to the
     *  {@linkplain #size() size}
     *  of the array, the element is appended to the end of the array.</p>
     *
     *  @param  index   The index.
     *  @param  value   The value to add.
     *  @throws IndexOutOfBoundsException   The index is less than 0 or greater
     *      than the
     *      {@linkplain #size() size}
     *      of the array.
     */
    public void add( final int index, final int value ) throws IndexOutOfBoundsException;

    /**
     *  <p>{@summary Inserts the given value as a new element to the given
     *  postion  this array and moves the other elements to a by one higher
     *  index.}</p>
     *  <p>If {@code index} is equals to the
     *  {@linkplain #size() size}
     *  of the array, the element is appended to the end of the array.</p>
     *
     *  @param  index   The index.
     *  @param  value   The value to add.
     *  @throws IndexOutOfBoundsException   The index is less than 0 or greater
     *      than the
     *      {@linkplain #size() size}
     *      of the array.
     */
    public void add( final int index, final Integer value ) throws IndexOutOfBoundsException;

    /**
     *  <p>{@summary Inserts the given value as a new element to the given
     *  postion  this array and moves the other elements to a by one higher
     *  index.}</p>
     *  <p>If {@code index} is equals to the
     *  {@linkplain #size() size}
     *  of the array, the element is appended to the end of the array.</p>
     *
     *  @param  index   The index.
     *  @param  value   The value to add.
     *  @throws IndexOutOfBoundsException   The index is less than 0 or greater
     *      than the
     *      {@linkplain #size() size}
     *      of the array.
     */
    public void add( final int index, final JSONValue value ) throws IndexOutOfBoundsException;

    /**
     *  <p>{@summary Inserts the given value as a new element to the given
     *  postion  this array and moves the other elements to a by one higher
     *  index.}</p>
     *  <p>If {@code index} is equals to the
     *  {@linkplain #size() size}
     *  of the array, the element is appended to the end of the array.</p>
     *
     *  @param  index   The index.
     *  @param  value   The value to add.
     *  @throws IndexOutOfBoundsException   The index is less than 0 or greater
     *      than the
     *      {@linkplain #size() size}
     *      of the array.
     */
    public void add( final int index, final long value ) throws IndexOutOfBoundsException;

    /**
     *  <p>{@summary Inserts the given value as a new element to the given
     *  postion  this array and moves the other elements to a by one higher
     *  index.}</p>
     *  <p>If {@code index} is equals to the
     *  {@linkplain #size() size}
     *  of the array, the element is appended to the end of the array.</p>
     *
     *  @param  index   The index.
     *  @param  value   The value to add.
     *  @throws IndexOutOfBoundsException   The index is less than 0 or greater
     *      than the
     *      {@linkplain #size() size}
     *      of the array.
     */
    public void add( final int index, final Long value ) throws IndexOutOfBoundsException;

    /**
     *  <p>{@summary Inserts the given value as a new element to the given
     *  postion  this array and moves the other elements to a by one higher
     *  index.}</p>
     *  <p>If {@code index} is equals to the
     *  {@linkplain #size() size}
     *  of the array, the element is appended to the end of the array.</p>
     *
     *  @param  index   The index.
     *  @param  value   The value to add.
     *  @throws IndexOutOfBoundsException   The index is less than 0 or greater
     *      than the
     *      {@linkplain #size() size}
     *      of the array.
     */
    public void add( final int index, final String value ) throws IndexOutOfBoundsException;

    /**
     *  <p>{@summary Appends the given array to this one.} This mean that the
     *  elements of the given array are added to this one.</p>
     *
     *  @param  array   The other array.
     *  @return {@code true} if the call to this method changed this instance.
     */
    @SuppressWarnings( "BooleanMethodNameMustStartWithQuestion" )
    public boolean addAll( final JSONArray array );

    /**
     *  <p>{@summary Adds a new empty instance of {@code JSONArray} to this
     *  array and returns that.}</p>
     *
     *  @return The freshly created {@code JSONArray}.
     */
    public JSONArray addArray();

    /**
     *  <p>{@summary Adds a new empty instance of {@code JSONArray} to this
     *  array at the given index and returns that.}</p>
     *
     *  @param  index   The index.
     *  @return The freshly created {@code JSONArray}.
     *  @throws IndexOutOfBoundsException   The index is less than 0 or greater
     *      than the
     *      {@linkplain #size() size}
     *      of the array.
     */
    public JSONArray addArray( final int index ) throws IndexOutOfBoundsException;

    /**
     *  <p>{@summary Adds a new empty instance of
     *  {@link JSONObject}
     *  to this array and returns that.}</p>
     *
     *  @return The freshly created {@code JSONObject}.
     */
    public JSONObject addObject();

    /**
     *  <p>{@summary Adds a new empty instance of
     *  {@link JSONObject}
     *  to this array at the given index and returns that.}</p>
     *
     *  @param  index   The index.
     *  @return The freshly created {@code JSONObject}.
     *  @throws IndexOutOfBoundsException   The index is less than 0 or greater
     *      than the
     *      {@linkplain #size() size}
     *      of the array.
     */
    public JSONObject addObject( final int index ) throws IndexOutOfBoundsException;

    /**
     *  <p>{@summary Returns the value of the element at the specied index in
     *  this array.}</p>
     *
     *  @param  index   The index of the element whose value is to be returned.
     *  @return The element; will never be {@code null}.
     *  @throws IndexOutOfBoundsException   The index is less than 0 or greater
     *      than or equal to the
     *      {@linkplain #size() size}
     *      of the array.
     */
    public JSONValue get( final int index ) throws IndexOutOfBoundsException;

    /**
     *  <p>{@summary Convenience method that returns the value with the
     *  specified index as a {@code JSONArray}.}</p>
     *
     *  @param  index   The index of the element whose value is to be returned.
     *  @return The value of the element with the specified index.
     *  @throws IllegalStateException   The element is not a JSON Array.
     *  @throws IndexOutOfBoundsException   The index is less than 0 or greater
     *      than or equal to the
     *      {@linkplain #size() size}
     *      of the array.
     */
    public default JSONArray getArray( final int index ) throws IllegalStateException, IndexOutOfBoundsException
    {
        final var value = get( index );
        final var retValue = value.asArray();

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  getArray()

    /**
     *  <p>{@summary Convenience method that returns the value with the
     *  specified index as a
     *  {@link BigDecimal}.}</p>
     *
     *  @param  index   The index of the element whose value is to be returned.
     *  @return The value of the element with the specified index.
     *  @throws IllegalStateException   The element is not a number.
     *  @throws NumberFormatException   The element is a number, but cannot be
     *      parsed to a valid {@code BigDecimal}.
     *  @throws IndexOutOfBoundsException   The index is less than 0 or greater
     *      than or equal to the
     *      {@linkplain #size() size}
     *      of the array.
     */
    public default BigDecimal getBigDecimal( final int index ) throws IllegalStateException, IndexOutOfBoundsException, NumberFormatException
    {
        final var value = get( index );
        final var number = value.asNumber();
        final var retValue = number.getBigDecimal();

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  getBigDecimal()

    /**
     *  <p>{@summary Convenience method that returns the value with the
     *  specified index as a
     *  {@link BigInteger}.}</p>
     *
     *  @param  index   The index of the element whose value is to be returned.
     *  @return The value of the element with the specified index.
     *  @throws IllegalStateException   The element is not a number.
     *  @throws NumberFormatException   The element is a number, but cannot be
     *      parsed to a valid {@code BigInteger}.
     *  @throws IndexOutOfBoundsException   The index is less than 0 or greater
     *      than or equal to the
     *      {@linkplain #size() size}
     *      of the array.
     */
    public default BigInteger getBigInteger( final int index ) throws IllegalStateException, IndexOutOfBoundsException, NumberFormatException
    {
        final var value = get( index );
        final var number = value.asNumber();
        final var retValue = number.getBigInteger();

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  getBigInteger()

    /**
     *  <p>{@summary Convenience method that returns the value with the
     *  specified index as a {@code boolean}.}</p>
     *
     *  @param  index   The index of the element whose value is to be returned.
     *  @return The value of the element with the specified index.
     *  @throws IllegalStateException   The element is not a boolean.
     *  @throws IndexOutOfBoundsException   The index is less than 0 or greater
     *      than or equal to the
     *      {@linkplain #size() size}
     *      of the array.
     */
    @SuppressWarnings( "BooleanMethodNameMustStartWithQuestion" )
    public default boolean getBoolean( final int index ) throws IllegalStateException, IndexOutOfBoundsException
    {
        final var value = get( index );
        final var flag = value.asBoolean();
        final var retValue = flag.isTrue();

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  getBoolean()

    /**
     *  <p>{@summary Convenience method that returns the value with the
     *  specified index as a {@code double}.}</p>
     *
     *  @param  index   The index of the element whose value is to be returned.
     *  @return The value of the element with the specified index.
     *  @throws IllegalStateException   The element is not a number.
     *  @throws NumberFormatException   The element is a number, but cannot be
     *      parsed to a valid {@code double}.
     *  @throws IndexOutOfBoundsException   The index is less than 0 or greater
     *      than or equal to the
     *      {@linkplain #size() size}
     *      of the array.
     */
    public default double getDouble( final int index ) throws IllegalStateException, IndexOutOfBoundsException, NumberFormatException
    {
        final var value = get( index );
        final var number = value.asNumber();
        final var retValue = number.getDouble();

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  getDouble()

    /**
     *  <p>{@summary Convenience method that returns the value with the
     *  specified index as a {@code float}.}</p>
     *
     *  @param  index   The index of the element whose value is to be returned.
     *  @return The value of the element with the specified index.
     *  @throws IllegalStateException   The element is not a number.
     *  @throws NumberFormatException   The element is a number, but cannot be
     *      parsed to a valid {@code float}.
     *  @throws IndexOutOfBoundsException   The index is less than 0 or greater
     *      than or equal to the
     *      {@linkplain #size() size}
     *      of the array.
     */
    public default float getFloat( final int index ) throws IllegalStateException, IndexOutOfBoundsException, NumberFormatException
    {
        final var value = get( index );
        final var number = value.asNumber();
        final var retValue = number.getFloat();

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  getFloat()

    /**
     *  <p>{@summary Convenience method that returns the value with the
     *  specified index as a {@code int}.}</p>
     *
     *  @param  index   The index of the element whose value is to be returned.
     *  @return The value of the element with the specified index.
     *  @throws IllegalStateException   The element is not a number.
     *  @throws NumberFormatException   The element is a number, but cannot be
     *      parsed to a valid {@code int}.
     *  @throws IndexOutOfBoundsException   The index is less than 0 or greater
     *      than or equal to the
     *      {@linkplain #size() size}
     *      of the array.
     */
    public default int getInt( final int index ) throws IllegalStateException, IndexOutOfBoundsException, NumberFormatException
    {
        final var value = get( index );
        final var number = value.asNumber();
        final var retValue = number.getInt();

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  getInt()

    /**
     *  <p>{@summary Convenience method that returns the value with the
     *  specified index as a {@code long}.}</p>
     *
     *  @param  index   The index of the element whose value is to be returned.
     *  @return The value of the element with the specified index.
     *  @throws IllegalStateException   The element is not a number.
     *  @throws NumberFormatException   The element is a number, but cannot be
     *      parsed to a valid {@code long}.
     *  @throws IndexOutOfBoundsException   The index is less than 0 or greater
     *      than or equal to the
     *      {@linkplain #size() size}
     *      of the array.
     */
    public default long getLong( final int index ) throws IllegalStateException, IndexOutOfBoundsException, NumberFormatException
    {
        final var value = get( index );
        final var number = value.asNumber();
        final var retValue = number.getLong();

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  getInt()

    /**
     *  <p>{@summary Convenience method that returns the value with the
     *  specified index as a
     *  {@link JSONObject}.}</p>
     *
     *  @param  index   The index of the element whose value is to be returned.
     *  @return The value of the element with the specified index.
     *  @throws IllegalStateException   The element is not a JSON Object.
     *  @throws IndexOutOfBoundsException   The index is less than 0 or greater
     *      than or equal to the
     *      {@linkplain #size() size}
     *      of the array.
     */
    public default JSONObject getObject( final int index ) throws IllegalStateException, IndexOutOfBoundsException
    {
        final var value = get( index );
        final var retValue = value.asObject();

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  getObject()

    /**
     *  <p>{@summary Convenience method that returns the value with the
     *  specified index as a
     *  {@link String}.}</p>
     *
     *  @param  index   The index of the element whose value is to be returned.
     *  @return The value of the element with the specified index.
     *  @throws IllegalStateException   The element is not a String.
     *  @throws IndexOutOfBoundsException   The index is less than 0 or greater
     *      than or equal to the
     *      {@linkplain #size() size}
     *      of the array.
     */
    public default String getString( final int index ) throws IllegalStateException, IndexOutOfBoundsException
    {
        final var value = get( index );
        final var jsonString = value.asString();
        final var retValue = jsonString.getString();

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  getString()

    /**
     *  Checks whether this array has elements.
     *
     *  @return {@code true} if the object does not have any elements,
     *      {@code false} otherwise.
     */
    public boolean isEmpty();

    /**
     *  <p>{@summary Removes the element with the specified index from this
     *  array.}</p>
     *
     *  @param  index   The index of the element to remove.
     *  @throws IndexOutOfBoundsException   The index is less than 0 or greater
     *      than or equal to the
     *      {@linkplain #size() size}
     *      of the array.
     */
    public void remove( final int index ) throws IndexOutOfBoundsException;

    /**
     *  <p>{@summary Removes the first occurrence of the specified element from
     *  this array, if it is present.} If this list does not contain the
     *  element, it remains unchanged.</p>
     *  <p>More formally, this method removes the element with the lowest index
     *  {@code i} such that
     *  {@link org.tquadrat.foundation.lang.Objects#equals(Object, Object) Objects.equals(o, get(i))}
     *  (if such an element exists).</p>
     *
     *  @param  element   The element to remove.
     */
    public void remove( final JSONValue element );

    /**
     *  <p>{@summary Sets the value of the element with the specified index to
     *  the JSON representation of the specified
     *  {@link BigDecimal}
     *  value.}</p>
     *
     *  @param  index   The index of the element to replace.
     *  @param  value   The value to set to the member.
     *  @throws IndexOutOfBoundsException   The index is less than 0 or greater
     *      than or equal to the
     *      {@linkplain #size() size}
     *      of the array.
     */
    public void set( final int index, final BigDecimal value ) throws IndexOutOfBoundsException;

    /**
     *  <p>{@summary Sets the value of the element with the specified index to
     *  the JSON representation of the specified
     *  {@link BigInteger}
     *  value.}</p>
     *
     *  @param  index   The index of the element to replace.
     *  @param  value   The value to set to the member.
     *  @throws IndexOutOfBoundsException   The index is less than 0 or greater
     *      than or equal to the
     *      {@linkplain #size() size}
     *      of the array.
     */
    public void set( final int index, final BigInteger value ) throws IndexOutOfBoundsException;

    /**
     *  <p>{@summary Sets the value of the element with the specified index to
     *  the JSON representation of the specified {@code boolean} value.}</p>
     *
     *  @param  index   The index of the element to replace.
     *  @param  value   The value to set to the member.
     *  @throws IndexOutOfBoundsException   The index is less than 0 or greater
     *      than or equal to the
     *      {@linkplain #size() size}
     *      of the array.
     */
    public default void set( final int index, final boolean value ) throws IndexOutOfBoundsException
    {
        set( index, value ? TRUE : FALSE );
    }   //  set()

    /**
     *  <p>{@summary Sets the value of the element with the specified index to
     *  the JSON representation of the specified
     *  {@link DimensionedValue}
     *  value.}</p>
     *
     *  @param  <T> The type of the dimension for the value.
     *  @param  index   The index.
     *  @param  value   The value.
     *  @param  targetUnit  The dimension for the output.
     *  @throws IndexOutOfBoundsException   The index is less than 0 or greater
     *      than or equal to the
     *      {@linkplain #size() size}
     *      of the array.
     *
     *  @see JSONBuilder#valueOf(DimensionedValue,Dimension)
     */
    public <T extends Dimension> void set( final int index, final DimensionedValue<T> value, final T targetUnit ) throws IndexOutOfBoundsException;

    /**
     *  <p>{@summary Sets the value of the element with the specified index to
     *  the JSON representation of the specified {@code double} value.}</p>
     *
     *  @param  index   The index of the element to replace.
     *  @param  value   The value to set to the member.
     *  @throws IndexOutOfBoundsException   The index is less than 0 or greater
     *      than or equal to the
     *      {@linkplain #size() size}
     *      of the array.
     */
    public void set( final int index, final double value ) throws IndexOutOfBoundsException;

    /**
     *  <p>{@summary Sets the value of the element with the specified index to
     *  the JSON representation of the specified
     *  {@link Double}
     *  value.}</p>
     *
     *  @param  index   The index of the element to replace.
     *  @param  value   The value to set to the member.
     *  @throws IndexOutOfBoundsException   The index is less than 0 or greater
     *      than or equal to the
     *      {@linkplain #size() size}
     *      of the array.
     */
    public void set( final int index, final Double value ) throws IndexOutOfBoundsException;

    /**
     *  <p>{@summary Sets the value of the element with the specified index to
     *  the JSON representation of the specified {@code int} value.}</p>
     *
     *  @param  index   The index of the element to replace.
     *  @param  value   The value to set to the member.
     *  @throws IndexOutOfBoundsException   The index is less than 0 or greater
     *      than or equal to the
     *      {@linkplain #size() size}
     *      of the array.
     */
    public void set( final int index, final int value ) throws IndexOutOfBoundsException;

    /**
     *  <p>{@summary Sets the value of the element with the specified index to
     *  the JSON representation of the specified {@code float} value.}</p>
     *
     *  @param  index   The index of the element to replace.
     *  @param  value   The value to set to the member.
     *  @throws IndexOutOfBoundsException   The index is less than 0 or greater
     *      than or equal to the
     *      {@linkplain #size() size}
     *      of the array.
     */
    public void set( final int index, final float value ) throws IndexOutOfBoundsException;

    /**
     *  <p>{@summary Sets the value of the element with the specified index to
     *  the JSON representation of the specified
     *  {@link Float}
     *  value.}</p>
     *
     *  @param  index   The index of the element to replace.
     *  @param  value   The value to set to the member.
     *  @throws IndexOutOfBoundsException   The index is less than 0 or greater
     *      than or equal to the
     *      {@linkplain #size() size}
     *      of the array.
     */
    public void set( final int index, final Float value ) throws IndexOutOfBoundsException;

    /**
     *  <p>{@summary Sets the value of the element with the specified index to
     *  the JSON representation of the specified
     *  {@link Integer}
     *  value.}</p>
     *
     *  @param  index   The index of the element to replace.
     *  @param  value   The value to set to the member.
     *  @throws IndexOutOfBoundsException   The index is less than 0 or greater
     *      than or equal to the
     *      {@linkplain #size() size}
     *      of the array.
     */
    public void set( final int index, final Integer value ) throws IndexOutOfBoundsException;

    /**
     *  <p>{@summary Sets the value of the element with the specified index to
     *  the given
     *  {@link JSONValue}
     *  instance.}</p>
     *
     *  @param  index   The index of the element to replace.
     *  @param  value   The value to set to the member.
     *  @throws IndexOutOfBoundsException   The index is less than 0 or greater
     *      than or equal to the
     *      {@linkplain #size() size}
     *      of the array.
     */
    public void set( final int index, final JSONValue value ) throws IndexOutOfBoundsException;

    /**
     *  <p>{@summary Sets the value of the element with the specified index to
     *  the JSON representation of the specified {@code long} value.}</p>
     *
     *  @param  index   The index of the element to replace.
     *  @param  value   The value to set to the member.
     *  @throws IndexOutOfBoundsException   The index is less than 0 or greater
     *      than or equal to the
     *      {@linkplain #size() size}
     *      of the array.
     */
    public void set( final int index, final long value ) throws IndexOutOfBoundsException;

    /**
     *  <p>{@summary Sets the value of the element with the specified index to
     *  the JSON representation of the specified
     *  {@link Long}
     *  value.}</p>
     *
     *  @param  index   The index of the element to replace.
     *  @param  value   The value to set to the member.
     *  @throws IndexOutOfBoundsException   The index is less than 0 or greater
     *      than or equal to the
     *      {@linkplain #size() size}
     *      of the array.
     */
    public void set( final int index, final Long value ) throws IndexOutOfBoundsException;

    /**
     *  <p>{@summary Sets the value of the element with the specified index to
     *  the JSON representation of the specified
     *  {@link String}
     *  value.}</p>
     *
     *  @param  index   The index of the element to replace.
     *  @param  value   The value to set to the member.
     *  @throws IndexOutOfBoundsException   The index is less than 0 or greater
     *      than or equal to the
     *      {@linkplain #size() size}
     *      of the array.
     */
    public void set( final int index, final String value ) throws IndexOutOfBoundsException;

    /**
     *  <p>{@summary Sets a new empty instance of {@code JSONArray} to this
     *  array at the given index and returns that.}</p>
     *
     *  @param  index   The index.
     *  @return The freshly created {@code JSONArray}.
     *  @throws IndexOutOfBoundsException   The index is less than 0 or greater
     *      than or equal to the
     *      {@linkplain #size() size}
     *      of the array.
     */
    public JSONArray setArray( final int index ) throws IndexOutOfBoundsException;

    /**
     *  <p>{@summary Sets a new empty instance of
     *  {@link JSONObject}
     *  to this array at the given index and returns that.}</p>
     *
     *  @param  index   The index.
     *  @return The freshly created {@code JSONObject}.
     *  @throws IndexOutOfBoundsException   The index is less than 0 or greater
     *      than or equal to the
     *      {@linkplain #size() size}
     *      of the array.
     */
    public JSONObject setObject( final int index ) throws IndexOutOfBoundsException;

    /**
     *  Returns the number of elements for this array.
     *
     * @return The number of members in this object.
     */
    public int size();
}
//  interface JSONObject

/*
 *  End of File
 */