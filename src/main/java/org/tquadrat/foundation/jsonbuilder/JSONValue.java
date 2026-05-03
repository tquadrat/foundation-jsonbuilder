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

import static org.apiguardian.api.API.Status.STABLE;

import java.util.Formattable;
import java.util.Formatter;

import org.apiguardian.api.API;
import org.tquadrat.foundation.annotation.ClassVersion;

/**
 *  <p>{@summary This interface describes the base for all the JSON
 *  datatypes.}</p>
 *  <p>{@code JSONValue} implements
 *  {@link Formattable}
 *  for the implementation of pretty-printed output through
 *  {@code String.format( "%s", value )} or similar.</p>
 *  <p>{@link #formatTo(Formatter,int,int,int)}
 *  ignores the arguments {@code flags} and {@code precision}, while the
 *  {@code width} argument is used internally. Therefore, a call like
 *  {@code String.format( "%10s", value )} may result in some unexpected
 *  output.</p>
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: JSONValue.java 1218 2026-05-02 15:17:24Z tquadrat $
 *  @since 0.25.0
 *
 *  @UMLGraph.link
 */
@ClassVersion( sourceVersion = "$Id: JSONValue.java 1218 2026-05-02 15:17:24Z tquadrat $" )
@API( status = STABLE, since = "0.25.0" )
public sealed interface JSONValue extends Formattable
    permits JSONArray, JSONLiteral, JSONNumber, JSONObject, JSONString
{
        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  <p>{@summary Returns this JSON value as
     *  {@link JSONArray},
     *  assuming that this value represents a JSON array.} If this is not the
     *  case, an
     *  {@link IllegalStateException}
     *  is thrown.
     *
     *  @return This {@code JSONValue} instance as a
     *      {@link JSONArray}.
     *  @throws IllegalStateException   This JSON value is not a JSON array.
     */
    @SuppressWarnings( "ClassReferencesSubclass" )
    public default JSONArray asArray()
    {
        if( !isArray() ) throw new IllegalStateException( "Not a JSONArray: %s".formatted( this.toString() ) );

        //---* Done *----------------------------------------------------------
        return (JSONArray) this;
    }   //  asArray()

    /**
     *  <p>{@summary Returns this JSON value as
     *  {@linkplain JSONLiteral JSON literals}
     *  {@link JSONLiteral#FALSE false}
     *  or
     *  {@link JSONLiteral#TRUE true},
     *  assuming that this value represents a JSON Boolean.} If this is not the
     *  case, an
     *  {@link IllegalStateException}
     *  is thrown.
     *
     *  @return This {@code JSONValue} instance as a
     *      {@link JSONLiteral}.
     *  @throws IllegalStateException   This JSON value is not a JSON Boolean.
     */
    @SuppressWarnings( "ClassReferencesSubclass" )
    public default JSONLiteral asBoolean()
    {
        if( !isBoolean() ) throw new IllegalStateException( "Not a JSON Boolean: %s".formatted( this.toString() ) );

        //---* Done *----------------------------------------------------------
        return (JSONLiteral) this;
    }   //  asBoolean()

    /**
     *  <p>{@summary Returns this JSON value as
     *  {@link JSONNumber},
     *  assuming that this value represents a JSON number.} If this is not the
     *  case, an
     *  {@link IllegalStateException}
     *  is thrown.
     *
     *  @return This {@code JSONValue} instance as a
     *      {@link JSONNumber}.
     *  @throws IllegalStateException   This JSON value is not a JSON String.
     */
    @SuppressWarnings( "ClassReferencesSubclass" )
    public default JSONNumber asNumber()
    {
        if( !isNumber() ) throw new IllegalStateException( "Not a JSONNumber: %s".formatted( this.toString() ) );

        //---* Done *----------------------------------------------------------
        return (JSONNumber) this;
    }   //  asNumber()

    /**
     *  <p>{@summary Returns this JSON value as
     *  {@link JSONObject},
     *  assuming that this value represents a JSON object.} If this is not the
     *  case, an
     *  {@link IllegalStateException}
     *  is thrown.
     *
     *  @return This {@code JSONValue} instance as a
     *      {@link JSONObject}.
     *  @throws IllegalStateException   This JSON value is not a JSON object.
     */
    @SuppressWarnings( "ClassReferencesSubclass" )
    public default JSONObject asObject()
    {
        if( !isObject() ) throw new IllegalStateException( "Not a JSONObject: %s".formatted( this.toString() ) );

        //---* Done *----------------------------------------------------------
        return (JSONObject) this;
    }   //  asObject()

    /**
     *  <p>{@summary Returns this JSON value as
     *  {@link JSONString},
     *  assuming that this value represents a JSON String.} If this is not the
     *  case, an
     *  {@link IllegalStateException}
     *  is thrown.
     *
     *  @return This {@code JSONValue} instance as a
     *      {@link JSONString}.
     *  @throws IllegalStateException   This JSON value is not a JSON String.
     */
    @SuppressWarnings( "ClassReferencesSubclass" )
    public default JSONString asString()
    {
        if( !isString() ) throw new IllegalStateException( "Not a JSONString: %s".formatted( this.toString() ) );

        //---* Done *----------------------------------------------------------
        return (JSONString) this;
    }   //  asString()

    /**
     *  {@inheritDoc}
     */
    @Override
    public void formatTo( final Formatter formatter, final int flags, final int width, final int precision );

    /**
     *  <p>{@summary Detects whether this value represents a boolean
     *  value.}</p>
     *
     *  @return {@code true} if this value represents either the
     *      {@linkplain JSONLiteral JSON literal}
     *      {@link JSONLiteral#TRUE true}
     *      or
     *      {@link JSONLiteral#FALSE}.
     */
    public default boolean isBoolean() { return false; }

    /**
     *  <p>{@summary Detects whether this value represents a JSON array.} If
     *  this is the case, this value is an instance of
     *  {@link JSONArray}.</p>
     *
     *  @return {@code true} if this value is an instance of
     *      {@link JSONArray}, {@code false} otherwise.
     */
    @SuppressWarnings( {"InstanceofThis", "ClassReferencesSubclass"} )
    public default boolean isArray() { return this instanceof JSONArray; }

    /**
     *  <p>{@summary Detects whether this value represents the JSON literal
     *  {@code false}.}</p>
     *
     *  @return {@code true} if this value represents the
     *      {@linkplain JSONLiteral JSON literal}
     *      {@link JSONLiteral#FALSE false}.
     */
    public default boolean isFalse() { return false; }

    /**
     *  <p>{@summary Detects whether this value represents the JSON literal
     *  {@code null}.}
     *
     *  @return {@code true} if this value represents the
     *      {@linkplain JSONLiteral JSON literal}
     *      {@link JSONLiteral#NULL null}.
     */
    public default boolean isNull() { return false; }

    /**
     *  <p>{@summary Detects whether this value represents a JSON number.} If
     *  this is the case, this value is an instance of
     *  {@link JSONNumber}.</p>
     *
     *  @return {@code true} if this value is an instance of
     *      {@link JSONObject}, {@code false} otherwise.
     */
    @SuppressWarnings( {"InstanceofThis", "ClassReferencesSubclass"} )
    public default boolean isNumber() { return this instanceof JSONNumber; }

    /**
     *  <p>{@summary Detects whether this value represents a JSON object.} If
     *  this is the case, this value is an instance of
     *  {@link JSONObject}.</p>
     *
     *  @return {@code true} if this value is an instance of
     *      {@link JSONObject}, {@code false} otherwise.
     */
    @SuppressWarnings( {"InstanceofThis", "ClassReferencesSubclass"} )
    public default boolean isObject() { return this instanceof JSONObject; }

    /**
     *  <p>{@summary Detects whether this value represents a JSON string.} If
     *  this is the case, this value is an instance of
     *  {@link JSONString}.</p>
     *
     *  @return {@code true} if this value is an instance of
     *      {@link JSONString}, {@code false} otherwise.
     */
    @SuppressWarnings( {"InstanceofThis", "ClassReferencesSubclass"} )
    public default boolean isString() { return this instanceof JSONString; }

    /**
     *  <p>{@summary Detects whether this value represents the JSON literal
     *  {@code true}.}</p>
     *
     *  @return {@code true} if this value represents the
     *      {@linkplain JSONLiteral JSON literal}
     *      {@link JSONLiteral#TRUE true}.
     */
    public default boolean isTrue() { return false; }

    /**
     *  {@inheritDoc}
     */
    @Override
    public String toString();
}
//  interface JSONValue

/*
 *  End of File
 */