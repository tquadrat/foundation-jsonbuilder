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
import org.tquadrat.foundation.jsonbuilder.internal.JSONObjectImpl;
import org.tquadrat.foundation.lang.value.Dimension;
import org.tquadrat.foundation.lang.value.DimensionedValue;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Optional;
import java.util.SequencedCollection;

import static org.apiguardian.api.API.Status.STABLE;
import static org.tquadrat.foundation.jsonbuilder.JSONLiteral.FALSE;
import static org.tquadrat.foundation.jsonbuilder.JSONLiteral.TRUE;

/**
 *  <p>{@summary The definition of a JSON object.}</p>
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: JSONObject.java 1195 2026-04-15 21:33:40Z tquadrat $
 *  @since 0.25.0
 *
 *  @UMLGraph.link
 */
@SuppressWarnings( "ClassWithTooManyMethods" )
@ClassVersion( sourceVersion = "$Id: JSONObject.java 1195 2026-04-15 21:33:40Z tquadrat $" )
@API( status = STABLE, since = "0.25.0" )
public sealed interface JSONObject extends Iterable<JSONValue>, JSONValue
    permits JSONObjectImpl
{
        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  <p>{@summary Checks if a specified member is present as a child of this
     *  object.} This will not test if that child is the JSON literal
     *  {@code null}, This needs to be tested separately.</p>
     *
     *  @param  name The name of the member to check for.
     *  @return {@code true} if there is a member with the given name,
     *      {@code false} if not.
     */
    public boolean contains( final String name );

    /**
     *  <p>{@summary Returns the value of the member with the specified name in
     *  this object.}</p>
     *
     *  @param  name    The name of the member whose value is to be returned.
     *  @return An instance of
     *      {@link Optional}
     *      that holds the value.
     */
    public Optional<JSONValue> get( final String name );

    /**
     *  <p>{@summary Convenience method that returns the value with the
     *  specified name as a
     *  {@link JSONArray}.}</p>
     *
     *  @param  name    The name of the member whose value is to be returned.
     *  @param  defaultValue    The value to be returned if the requested
     *      member is missing; can be {@code null}.
     *  @return The value of the member with the specified name, or the given
     *      default value if this object does not contain a member with that
     *      name.
     *  @throws IllegalStateException   The member exists, but it is not a
     *      JSON Array.
     */
    public default JSONArray getArray( final String name, final JSONArray defaultValue ) throws IllegalStateException
    {
        final var retValue = get( name ).map( JSONValue::asArray ).orElse( defaultValue );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  getArray()

    /**
     *  <p>{@summary Convenience method that returns the value with the
     *  specified name as a
     *  {@link BigDecimal}.}</p>
     *
     *  @param  name    The name of the member whose value is to be returned.
     *  @param  defaultValue    The value to be returned if the requested
     *      member is missing; can be {@code null}.
     *  @return The value of the member with the specified name, or the given
     *      default value if this object does not contain a member with that
     *      name.
     *  @throws IllegalStateException   The member exists, but it is not a
     *      number.
     *  @throws NumberFormatException   The member exists, and it is a number,
     *      but cannot be parsed to a valid {@code BigDecimal}.
     */
    public default BigDecimal getBigDecimal( final String name, final BigDecimal defaultValue ) throws IllegalStateException, NumberFormatException
    {
        var retValue = defaultValue;
        final var value = get( name );
        if( value.isPresent() )
        {
            final var number = value.get().asNumber();
            retValue = number.getBigDecimal();
        }

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  getBigDecimal()

    /**
     *  <p>{@summary Convenience method that returns the value with the
     *  specified name as a
     *  {@link BigInteger}.}</p>
     *
     *  @param  name    The name of the member whose value is to be returned.
     *  @param  defaultValue    The value to be returned if the requested
     *      member is missing; can be {@code null}.
     *  @return The value of the member with the specified name, or the given
     *      default value if this object does not contain a member with that
     *      name.
     *  @throws IllegalStateException   The member exists, but it is not a
     *      number.
     *  @throws NumberFormatException   The member exists, and it is a number,
     *      but cannot be parsed to a valid {@code BigInteger}.
     */
    public default BigInteger getBigInteger( final String name, final BigInteger defaultValue ) throws IllegalStateException, NumberFormatException
    {
        var retValue = defaultValue;
        final var value = get( name );
        if( value.isPresent() )
        {
            final var number = value.get().asNumber();
            retValue = number.getBigInteger();
        }

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  getBigInteger()

    /**
     *  <p>{@summary Convenience method that returns the value with the
     *  specified name as a {@code boolean}.}</p>
     *
     *  @param  name    The name of the member whose value is to be returned.
     *  @param  defaultValue    The value to be returned if the requested
     *      member is missing.
     *  @return The value of the member with the specified name, or the given
     *      default value if this object does not contain a member with that
     *      name.
     *  @throws IllegalStateException   The member exists, but it is not a
     *      boolean.
     */
    @SuppressWarnings( "BooleanMethodNameMustStartWithQuestion" )
    public default boolean getBoolean( final String name, final boolean defaultValue ) throws IllegalStateException
    {
        var retValue = defaultValue;
        final var value = get( name );
        if( value.isPresent() )
        {
            final var flag = value.get().asBoolean();
            retValue = flag.isTrue();
        }

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  getBoolean()

    /**
     *  <p>{@summary Convenience method that returns the value with the
     *  specified name as a {@code double}.}</p>
     *
     *  @param  name    The name of the member whose value is to be returned.
     *  @param  defaultValue    The value to be returned if the requested
     *      member is missing.
     *  @return The value of the member with the specified name, or the given
     *      default value if this object does not contain a member with that
     *      name.
     *  @throws IllegalStateException   The member exists, but it is not a
     *      number.
     *  @throws NumberFormatException   The member exists, and it is a number,
     *      but cannot be parsed to a valid {@code double}.
     */
    public default double getDouble( final String name, final double defaultValue ) throws IllegalStateException, NumberFormatException
    {
        var retValue = defaultValue;
        final var value = get( name );
        if( value.isPresent() )
        {
            final var number = value.get().asNumber();
            retValue = number.getDouble();
        }

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  getDouble()

    /**
     *  <p>{@summary Convenience method that returns the value with the
     *  specified name as a {@code float}.}</p>
     *
     *  @param  name    The name of the member whose value is to be returned.
     *  @param  defaultValue    The value to be returned if the requested
     *      member is missing.
     *  @return The value of the member with the specified name, or the given
     *      default value if this object does not contain a member with that
     *      name.
     *  @throws IllegalStateException   The member exists, but it is not a
     *      number.
     *  @throws NumberFormatException   The member exists, and it is a number,
     *      but cannot be parsed to a valid {@code float}.
     */
    public default float getFloat( final String name, final float defaultValue ) throws IllegalStateException, NumberFormatException
    {
        var retValue = defaultValue;
        final var value = get( name );
        if( value.isPresent() )
        {
            final var number = value.get().asNumber();
            retValue = number.getFloat();
        }

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  getFloat()

    /**
     *  <p>{@summary Convenience method that returns the value with the
     *  specified name as a {@code int}.}</p>
     *
     *  @param  name    The name of the member whose value is to be returned.
     *  @param  defaultValue    The value to be returned if the requested
     *      member is missing; can be {@code null}.
     *  @return The value of the member with the specified name, or the given
     *      default value if this object does not contain a member with that
     *      name.
     *  @throws IllegalStateException   The member exists, but it is not a
     *      number.
     *  @throws NumberFormatException   The member exists, and it is a number,
     *      but cannot be parsed to a valid {@code int}.
     */
    public default int getInt( final String name, final int defaultValue ) throws IllegalStateException, NumberFormatException
    {
        var retValue = defaultValue;
        final var value = get( name );
        if( value.isPresent() )
        {
            final var number = value.get().asNumber();
            retValue = number.getInt();
        }

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  getInt()

    /**
     *  <p>{@summary Convenience method that returns the value with the
     *  specified name as a {@code long}.}</p>
     *
     *  @param  name    The name of the member whose value is to be returned.
     *  @param  defaultValue    The value to be returned if the requested
     *      member is missing.
     *  @return The value of the member with the specified name, or the given
     *      default value if this object does not contain a member with that
     *      name.
     *  @throws IllegalStateException   The member exists, but it is not a
     *      number.
     *  @throws NumberFormatException   The member exists, and it is a number,
     *      but cannot be parsed to a valid {@code long}.
     */
    public default long getLong( final String name, final long defaultValue ) throws IllegalStateException, NumberFormatException
    {
        var retValue = defaultValue;
        final var value = get( name );
        if( value.isPresent() )
        {
            final var number = value.get().asNumber();
            retValue = number.getLong();
        }

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  getLong()

    /**
     *  <p>{@summary Convenience method that returns the value with the
     *  specified name as a {@code JSONObject}.}</p>
     *
     *  @param  name    The name of the member whose value is to be returned.
     *  @param  defaultValue    The value to be returned if the requested
     *      member is missing; can be {@code null}.
     *  @return The value of the member with the specified name, or the given
     *      default value if this object does not contain a member with that
     *      name.
     *  @throws IllegalStateException   The member exists, but it is not a
     *      JSON Object.
     */
    public default JSONObject getObject( final String name, final JSONObject defaultValue ) throws IllegalStateException
    {
        final var retValue = get( name ).map( JSONValue::asObject ).orElse( defaultValue );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  getObject()

    /**
     *  <p>{@summary Convenience method that returns the value with the
     *  specified name as a
     *  {@link String}.}</p>
     *
     *  @param  name    The name of the member whose value is to be returned.
     *  @param  defaultValue    The value to be returned if the requested
     *      member is missing; can be {@code null}.
     *  @return The value of the member with the specified name, or the given
     *      default value if this object does not contain a member with that
     *      name.
     *  @throws IllegalStateException   The member exists, but it is not a
     *      {@code String}.
     */
    public default String getString( final String name, final String defaultValue ) throws IllegalStateException
    {
        var retValue = defaultValue;
        final var value = get( name );
        if( value.isPresent() )
        {
            final var jsonString = value.get().asString();
            retValue = jsonString.getString();
        }

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  getString()

    /**
     *  Checks whether this object has members.
     *
     *  @return {@code true} if the object does not have any members,
     *      {@code false} otherwise.
     */
    public boolean isEmpty();

    /**
     *  <p>{@summary Copies all members of the specified object into this
     *  object.} When the specified object contains members with names that
     *  also exist in this object, the existing values in this object will be
     *  replaced by the corresponding values in the specified object.</p>
     *
     *  @param  object  The object to merge.
     *  @return This object itself, to enable method chaining.
     */
    public JSONObject merge( final JSONObject object );

    /**
     *  <p>{@summary Returns the names of the members of this object in
     *  document order.}</p>
     *  <p>The return value is backed by this object and will reflect
     *  subsequent changes. It cannot be used to modify this object. Attempts
     *  to modify the returned data structure will result in an exception.</p>
     *
     *  @return The names.
     */
    public SequencedCollection<String> names();

    /**
     *  <p>{@summary Removes a member with the specified name from this
     *  object.} If this object does not contain a member with the specified
     *  name, the object is not modified.</p>
     *
     *  @param  name    The name of the member to remove.
     *  @return This object itself, to enable method chaining.
     */
    public JSONObject remove( final String name );

    /**
     *  <p>{@summary Sets the value of the member with the specified name to
     *  the JSON representation of the specified
     *  {@link BigDecimal}
     *  value.}</p>
     *  <p>If this object does not contain a member with this name, a new
     *  member is added at the end of the object.</p>
     *
     *  @param  name    The name of the member to replace or to add.
     *  @param  value   The value to set to the member.
     *  @return This object itself, to enable method chaining.
     */
    public JSONObject set( final String name, final BigDecimal value );

    /**
     *  <p>{@summary Sets the value of the member with the specified name to
     *  the JSON representation of the specified
     *  {@link BigInteger}
     *  value.}</p>
     *  <p>If this object does not contain a member with this name, a new
     *  member is added at the end of the object.</p>
     *
     *  @param  name    The name of the member to replace or to add.
     *  @param  value   The value to set to the member.
     *  @return This object itself, to enable method chaining.
     */
    public JSONObject set( final String name, final BigInteger value );

    /**
     *  <p>{@summary Sets the value of the member with the specified name to
     *  the JSON representation of the specified {@code boolean} value.}</p>
     *  <p>If this object does not contain a member with this name, a new
     *  member is added at the end of the object.</p>
     *
     *  @param  name    The name of the member to replace or to add.
     *  @param  value   The value to set to the member.
     *  @return This object itself, to enable method chaining.
     */
    public default JSONObject set( final String name, final boolean value )
    {
        return set( name, value ? TRUE : FALSE );
    }   //  set()

    /**
     *  <p>{@summary Sets the value of the member with the specified name to
     *  the JSON representation of the specified
     *  {@link DimensionedValue }
     *  value.}</p>
     *  <p>If this object does not contain a member with this name, a new
     *  member is added at the end of the object.</p>
     *
     *  @param  <T> The type of the dimension for the value.
     *  @param  name    The name of the member to replace or to add.
     *  @param  value   The value.
     *  @param  targetUnit  The dimension for the output.
     *  @return This object itself, to enable method chaining.
     *
     *  @see JSONBuilder#valueOf(DimensionedValue, Dimension)
     */
    public <T extends Dimension> JSONObject set( final String name, final DimensionedValue<T> value, final T targetUnit );

    /**
     *  <p>{@summary Sets the value of the member with the specified name to
     *  the JSON representation of the specified {@code double} value.}</p>
     *  <p>If this object does not contain a member with this name, a new
     *  member is added at the end of the object.</p>
     *
     *  @param  name    The name of the member to replace or to add.
     *  @param  value   The value to set to the member.
     *  @return This object itself, to enable method chaining.
     */
    public JSONObject set( final String name, final double value );

    /**
     *  <p>{@summary Sets the value of the member with the specified name to
     *  the JSON representation of the specified
     *  {@link Double}
     *  value.}</p>
     *  <p>If this object does not contain a member with this name, a new
     *  member is added at the end of the object.</p>
     *
     *  @param  name    The name of the member to replace or to add.
     *  @param  value   The value to set to the member.
     *  @return This object itself, to enable method chaining.
     */
    public JSONObject set( final String name, final Double value );

    /**
     *  <p>{@summary Sets the value of the member with the specified name to
     *  the JSON representation of the specified {@code float} value.}</p>
     *  <p>If this object does not contain a member with this name, a new
     *  member is added at the end of the object.</p>
     *
     *  @param  name    The name of the member to replace or to add.
     *  @param  value   The value to set to the member.
     *  @return This object itself, to enable method chaining.
     */
    public JSONObject set( final String name, final float value );

    /**
     *  <p>{@summary Sets the value of the member with the specified name to
     *  the JSON representation of the specified
     *  {@link Float}
     *  value.}</p>
     *  <p>If this object does not contain a member with this name, a new
     *  member is added at the end of the object.</p>
     *
     *  @param  name    The name of the member to replace or to add.
     *  @param  value   The value to set to the member.
     *  @return This object itself, to enable method chaining.
     */
    public JSONObject set( final String name, final Float value );

    /**
     *  <p>{@summary Sets the value of the member with the specified name to
     *  the JSON representation of the specified {@code int} value.}</p>
     *  <p>If this object does not contain a member with this name, a new
     *  member is added at the end of the object.</p>
     *
     *  @param  name    The name of the member to replace or to add.
     *  @param  value   The value to set to the member.
     *  @return This object itself, to enable method chaining.
     */
    public JSONObject set( final String name, final int value );

    /**
     *  <p>{@summary Sets the value of the member with the specified name to
     *  the JSON representation of the specified
     *  {@link Integer}
     *  value.}</p>
     *  <p>If this object does not contain a member with this name, a new
     *  member is added at the end of the object.</p>
     *
     *  @param  name    The name of the member to replace or to add.
     *  @param  value   The value to set to the member.
     *  @return This object itself, to enable method chaining.
     */
    public JSONObject set( final String name, final Integer value );

    /**
     *  <p>{@summary Sets the value of the member with the specified name to
     *  the specified
     *  {@link JSONValue}
     *  value.}</p>
     *  <p>If this object does not contain a member with this name, a new
     *  member is added at the end of the object.</p>
     *
     *  @param  name    The name of the member to replace or to add.
     *  @param  value   The value to set to the member.
     *  @return This object itself, to enable method chaining.
     */
    public JSONObject set( final String name, final JSONValue value );

    /**
     *  <p>{@summary Sets the value of the member with the specified name to
     *  the JSON representation of the specified {@code long} value.}</p>
     *  <p>If this object does not contain a member with this name, a new
     *  member is added at the end of the object.</p>
     *
     *  @param  name    The name of the member to replace or to add.
     *  @param  value   The value to set to the member.
     *  @return This object itself, to enable method chaining.
     */
    public JSONObject set( final String name, final long value );

    /**
     *  <p>{@summary Sets the value of the member with the specified name to
     *  the JSON representation of the specified
     *  {@link Long}
     *  value.}</p>
     *  <p>If this object does not contain a member with this name, a new
     *  member is added at the end of the object.</p>
     *
     *  @param  name    The name of the member to replace or to add.
     *  @param  value   The value to set to the member.
     *  @return This object itself, to enable method chaining.
     */
    public JSONObject set( final String name, final Long value );

    /**
     *  <p>{@summary Sets the value of the member with the specified name to
     *  the JSON representation of the specified
     *  {@link String}
     *  value.}</p>
     *  <p>If this object does not contain a member with this name, a new
     *  member is added at the end of the object.</p>
     *
     *  @param  name    The name of the member to replace or to add.
     *  @param  value   The value to set to the member.
     *  @return This object itself, to enable method chaining.
     */
    public JSONObject set( final String name, final String value );

    /**
     *  <p>{@summary Sets a new empty instance of
     *  {@link JSONArray}
     *  to this object and returns that.}</p>
     *
     *  @param  name    The name of the member.
     *  @return The freshly created {@code JSONArray}.
     */
    public JSONArray setArray( final String name );

    /**
     *  <p>{@summary Sets a new empty instance of {@code JSONObject} to this
     *  object and returns that.}</p>
     *
     *  @param  name    The name of the member.
     *  @return The freshly created {@code JSONObject}.
     */
    public JSONObject setObject( final String name );

    /**
     *  <p>{@summary Returns the number of members (name/value pairs) in this
     *  object.}</p>
     *
     *  @return The number of members in this object.
     */
    public int size();
}
//  interface JSONObject

/*
 *  End of File
 */