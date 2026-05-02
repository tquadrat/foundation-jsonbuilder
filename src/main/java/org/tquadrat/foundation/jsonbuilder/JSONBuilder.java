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
import org.tquadrat.foundation.jsonbuilder.internal.JSONBuilderImpl;
import org.tquadrat.foundation.jsonbuilder.internal.JSONObjectImpl;
import org.tquadrat.foundation.lang.value.Dimension;
import org.tquadrat.foundation.lang.value.DimensionedValue;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Formatter;
import java.util.function.Function;
import java.util.function.Supplier;

import static org.apiguardian.api.API.Status.STABLE;
import static org.tquadrat.foundation.jsonbuilder.JSONLiteral.FALSE;
import static org.tquadrat.foundation.jsonbuilder.JSONLiteral.NULL;
import static org.tquadrat.foundation.jsonbuilder.JSONLiteral.TRUE;
import static org.tquadrat.foundation.lang.Objects.mapFromNull;
import static org.tquadrat.foundation.lang.Objects.requireNonNullArgument;

/**
 *  <p>{@summary This sealed interface is the main API for the Foundation JSON
 *  Builder Library.}</p>
 *  <p>{@link #getInstance()} returns an instance of an implementation for the
 *  interface.</p>
 *  <p>Basically, the instances of {@code JSONBuilder} are stateless, and
 *  therefore they can be treated as thread-safe. Only the
 *  {@linkplain #getIndentation() indentation}
 *  that is used for pretty-printing the resulting JSON values may cause issues
 *  when different threads are using different values.</p>
 *  <p>The different implementations of
 *  {@link JSONValue}
 *  – particularly
 *  {@link JSONObject}
 *  and
 *  {@link JSONArray}
 *  are <i>not</i> thread-safe.</p>
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: JSONBuilder.java 1196 2026-04-18 14:31:50Z tquadrat $
 *  @since 0.25.0
 *
 *  @UMLGraph.link
 */
@ClassVersion( sourceVersion = "$Id: JSONBuilder.java 1196 2026-04-18 14:31:50Z tquadrat $" )
@API( status = STABLE, since = "0.25.0" )
public sealed interface JSONBuilder
    permits JSONBuilderImpl
{
        /*-----------*\
    ====** Constants **========================================================
        \*-----------*/
    /**
     *  The indentation that is used when a
     *  {@link JSONValue}
     *  is formatted: {@value}.
     *
     *  @see JSONValue#formatTo(Formatter,int,int,int)
     */
    public static final int DEFAULT_INDENTATION = 2;

    /**
     *  The name of the JSON String that holds the unit for the dimension from
     *  a
     *  {@link DimensionedValue}:
     *  {@value}.
     *
     *  @see #valueOf(DimensionedValue,Dimension)
     */
    public static final String JSONField_Unit = "Unit";

    /**
     *  The name of the JSON Number that holds the value from a
     *  {@link DimensionedValue}:
     *  {@value}.
     *
     *  @see #valueOf(DimensionedValue,Dimension)
     */
    public static final String JSONField_Value = "Value";

        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  <p>{@summary Returns a deep copy of the given JSON array.}</p>
     *
     *  @param  source  The array to copy.
     *  @return The deep copy.
     */
    public default JSONArray copyArray( final JSONArray source )
    {
        final var retValue = new JSONArrayImpl( (JSONArrayImpl) requireNonNullArgument( source, "source" ) );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  copyArray()

    /**
     *  <p>{@summary Returns a deep copy of the given JSON object.}</p>
     *
     *  @param  source  The object to copy.
     *  @return The deep copy.
     */
    public default JSONObject copyObject( final JSONObject source )
    {
        final var retValue = new JSONObjectImpl( (JSONObjectImpl) requireNonNullArgument( source, "source" ) );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  copyObject()

    /**
     *  <p>{@summary Returns a new empty instance of
     *  {@link JSONArray}.}</p>
     *
     *  @return The new array.
     */
    public JSONArray createArray();

    /**
     *  <p>{@summary Returns a new instance of
     *  {@link JSONArray}
     *  with the given number of elements, all set to
     *  {@link JSONLiteral#NULL}.}</p>
     *
     *  @param  elementCount    The number of elements for the new array.
     *  @return The new array.
     */
    public default JSONArray createArray( final int elementCount )
    {
        final var retValue = createArray();
        for( var i = 0; i < elementCount; ++i ) retValue.add( NULL );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  createArray()

    /**
     *  <p>{@summary Returns a new instance of
     *  {@link JSONArray}
     *  that is populated with the values from the given array.}</p>
     *
     *  @param  values  The values.
     *  @return The new array.
     */
    public default JSONArray createArray( final BigDecimal [] values )
    {
        final var retValue = createArray();
        for( final var value : requireNonNullArgument( values, "values" ) )
        {
            retValue.add( mapFromNull( valueOf( value ), NULL ) );
        }

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  createArray()

    /**
     *  <p>{@summary Returns a new instance of
     *  {@link JSONArray}
     *  that is populated with the values from the given array.}</p>
     *
     *  @param  values  The values.
     *  @return The new array.
     */
    public default JSONArray createArray( final BigInteger [] values )
    {
        final var retValue = createArray();
        for( final var value : requireNonNullArgument( values, "values" ) )
        {
            retValue.add( mapFromNull( valueOf( value ), NULL ) );
        }

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  createArray()

    /**
     *  <p>{@summary Returns a new instance of
     *  {@link JSONArray}
     *  that is populated with the values from the given array.}</p>
     *
     *  @param  values  The values.
     *  @return The new array.
     */
    public default JSONArray createArray( final double [] values )
    {
        final var retValue = createArray();
        for( final var value : requireNonNullArgument( values, "values" ) )
        {
            retValue.add( valueOf( value ) );
        }

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  createArray()

    /**
     *  <p>{@summary Returns a new instance of
     *  {@link JSONArray}
     *  that is populated with the values from the given array.}</p>
     *
     *  @param  values  The values.
     *  @return The new array.
     */
    public default JSONArray createArray( final Double [] values )
    {
        final var retValue = createArray();
        for( final var value : requireNonNullArgument( values, "values" ) )
        {
            retValue.add( mapFromNull( valueOf( value ), NULL ) );
        }

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  createArray()

    /**
     *  <p>{@summary Returns a new instance of
     *  {@link JSONArray}
     *  that is populated with the values from the given array.}</p>
     *
     *  @param  values  The values.
     *  @return The new array.
     */
    public default JSONArray createArray( final float [] values )
    {
        final var retValue = createArray();
        for( final var value : requireNonNullArgument( values, "values" ) )
        {
            retValue.add( valueOf( value ) );
        }

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  createArray()

    /**
     *  <p>{@summary Returns a new instance of
     *  {@link JSONArray}
     *  that is populated with the values from the given array.}</p>
     *
     *  @param  values  The values.
     *  @return The new array.
     */
    public default JSONArray createArray( final Float [] values )
    {
        final var retValue = createArray();
        for( final var value : requireNonNullArgument( values, "values" ) )
        {
            retValue.add( mapFromNull( valueOf( value ), NULL ) );
        }

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  createArray()

    /**
     *  <p>{@summary Returns a new instance of
     *  {@link JSONArray}
     *  that is populated with the values from the given array.}</p>
     *
     *  @param  values  The values.
     *  @return The new array.
     */
    public default JSONArray createArray( final int [] values )
    {
        final var retValue = createArray();
        for( final var value : requireNonNullArgument( values, "values" ) )
        {
            retValue.add( valueOf( value ) );
        }

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  createArray()

    /**
     *  <p>{@summary Returns a new instance of
     *  {@link JSONArray}
     *  that is populated with the values from the given array.}</p>
     *
     *  @param  values  The values.
     *  @return The new array.
     */
    public default JSONArray createArray( final Integer [] values )
    {
        final var retValue = createArray();
        for( final var value : requireNonNullArgument( values, "values" ) )
        {
            retValue.add( mapFromNull( valueOf( value ), NULL ) );
        }

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  createArray()

    /**
     *  <p>{@summary Returns a new instance of
     *  {@link JSONArray}
     *  that is populated with the values from the given array.}</p>
     *
     *  @param  values  The values.
     *  @return The new array.
     */
    public default JSONArray createArray( final JSONValue [] values )
    {
        final var retValue = createArray();
        for( final var value : requireNonNullArgument( values, "values" ) )
        {
            retValue.add( mapFromNull( value, NULL ) );
        }

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  createArray()

    /**
     *  <p>{@summary Returns a new instance of
     *  {@link JSONArray}
     *  that is populated with the values from the given array.}</p>
     *
     *  @param  values  The values.
     *  @return The new array.
     */
    public default JSONArray createArray( final long [] values )
    {
        final var retValue = createArray();
        for( final var value : requireNonNullArgument( values, "values" ) )
        {
            retValue.add( valueOf( value ) );
        }

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  createArray()

    /**
     *  <p>{@summary Returns a new instance of
     *  {@link JSONArray}
     *  that is populated with the values from the given array.}</p>
     *
     *  @param  values  The values.
     *  @return The new array.
     */
    public default JSONArray createArray( final Long [] values )
    {
        final var retValue = createArray();
        for( final var value : requireNonNullArgument( values, "values" ) )
        {
            retValue.add( mapFromNull( valueOf( value ), NULL ) );
        }

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  createArray()

    /**
     *  <p>{@summary Returns a new instance of
     *  {@link JSONArray}
     *  that is populated with the return value from the given
     *  {@link Supplier Supplier&lt;JSONValue []&gt;}.}</p>
     *
     *  @param  supplier    The supplier for the array.
     *  @return The new array.
     */
    public default JSONArray createArray( final Supplier<JSONValue []> supplier )
    {
        final var retValue = createArray();
        for( final var value : requireNonNullArgument( supplier, "supplier" ).get() )
        {
            retValue.add( mapFromNull( value, NULL ) );
        }

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  createArray()

    /**
     *  <p>{@summary Returns a new instance of
     *  {@link JSONArray}
     *  that is populated with the values from the given array.}</p>
     *
     *  @param  values  The values.
     *  @return The new array.
     */
    public default JSONArray createArray( final String... values )
    {
        final var retValue = createArray();
        for( final var value : requireNonNullArgument( values, "values" ) )
        {
            retValue.add( mapFromNull( valueOf( value ), NULL ) );
        }

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  createArray()

    /**
     *  <p>{@summary Returns a new empty instance of
     *  {@link JSONObject}.}</p>
     *
     *  @return The new object.
     */
    public JSONObject createObject();

    /**
     *  <p>{@summary Returns a new instance of
     *  {@link JSONObject}
     *  that is populated with the values returned by the given
     *  {@link Function}.}</p>
     *  <p>The function's argument is the name of the new member, provided by
     *  the {@code names} argument to this method.</p>
     *
     *  @param  names   The member names for the new object.
     *  @param  provider    The function that returns the value for the member
     *      with the given name.
     *  @return The new object.
     */
    public default JSONObject createObject( final Collection<String> names, final Function<String,JSONValue> provider )
    {
        requireNonNullArgument( provider, "provider" );
        final var retValue = createObject();
        for( final var name : requireNonNullArgument( names, "names" ) )
        {
            retValue.set( name, provider.apply( name ) );
        }

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  createObject()

    /**
     *  <p>{@summary Returns the indentation that is used when a
     *  {@link JSONValue}
     *  is formatted.} If not set explicitly through a call to
     *  {@link #setIndentation(int)},
     *  the default value
     *  ({@value DEFAULT_INDENTATION})
     *  will be returned.</p>
     *
     *  @return The indentation.
     *
     *  @see #DEFAULT_INDENTATION
     *  @see JSONValue#formatTo(Formatter,int,int,int)
     */
    public int getIndentation();

    /**
     *  <p>{@summary Sets the indentation that is used when a
     *  {@link JSONValue}
      *  is formatted for pretty-printing.}
     *
     *  @param  value   The indentation.
     *
     *  @see JSONValue#formatTo(Formatter,int,int,int)
     */
    public void setIndentation( final int value );

    /**
     *  <p>{@summary Creates an instance of {@code JSONBuilder}.}</p>
     *  <p>Each call to this method will return a new instance of
     *  {@code JSONBuilder}.</p>
     *
     *  @return A new instance of {@code JSONBuilder}.
     */
    public static JSONBuilder getInstance() { return new JSONBuilderImpl(); }

    /**
     *  Returns a
     *  {@link JSONValue}
     *  instance that represents the given
     *  {@link BigDecimal}
     *  value.
     *
     *  @param  value   The value.
     *  @return The JSON value that represents the given value.
     */
    public JSONNumber valueOf( final BigDecimal value );

    /**
     *  Returns a
     *  {@link JSONValue}
     *  instance that represents the given
     *  {@link BigInteger}
     *  value.
     *
     *  @param  value   The value
     *  @return The JSON value that represents the given value.
     */
    public JSONNumber valueOf( final BigInteger value );

    /**
     *  Returns a
     *  {@link JSONValue}
     *  instance that represents the given {@code boolean} value.
     *
     *  @param  value   The value
     *  @return The JSON value that represents the given value.
     */
    public default JSONLiteral valueOf( final boolean value )
    {
        final var retValue = value ? TRUE : FALSE;

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  valueOf()

    /**
     *  <p>{@summary Returns a
     *  {@link JSONValue}
     *  instance (more precisely, an instance of
     *  {@link JSONObject})
     *  that represents the given
     *  {@link DimensionedValue}
     *  instance.}</p>
     *  <p>The member names are
     *  {@value #JSONField_Unit}
     *  for the dimension, and
     *  {@value #JSONField_Value}
     *  for the numerical value.</p>
     *
     *  @param  <T> The type of the dimension for the value.
     *  @param  value   The value.
     *  @param  targetUnit  The dimension for the output.
     *  @return The resulting JSON object.
     */
    public default <T extends Dimension> JSONObject valueOf( final DimensionedValue<T> value, final T targetUnit )
    {
        final var retValue = createObject();
        retValue.set( JSONField_Unit, requireNonNullArgument( targetUnit, "targetUnit" ).unitSymbolForPrinting() );
        retValue.set( JSONField_Value, requireNonNullArgument( value, "value" ).convert( targetUnit ) );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  valueOf()

    /**
     *  Returns a
     *  {@link JSONValue}
     *  instance that represents the given {@code double} value.
     *
     *  @param  value   The value
     *  @return The JSON value that represents the given value.
     */
    public JSONNumber valueOf( final double value );

    /**
     *  Returns a
     *  {@link JSONValue}
     *  instance that represents the given
     *  {@link Double}
     *  value.
     *
     *  @param  value   The value
     *  @return The JSON value that represents the given value.
     */
    public JSONNumber valueOf( final Double value );

    /**
     *  Returns a
     *  {@link JSONValue}
     *  instance that represents the given {@code float} value.
     *
     *  @param  value   The value
     *  @return The JSON value that represents the given value.
     */
    public JSONNumber valueOf( final float value );

    /**
     *  Returns a
     *  {@link JSONValue}
     *  instance that represents the given
     *  {@link Float}
     *  value.
     *
     *  @param  value   The value
     *  @return The JSON value that represents the given value.
     */
    public JSONNumber valueOf( final Float value );

    /**
     *  Returns a
     *  {@link JSONValue}
     *  instance that represents the given {@code int} value.
     *
     *  @param  value   The value
     *  @return The JSON value that represents the given value.
     */
    public JSONNumber valueOf( final int value );

    /**
     *  Returns a
     *  {@link JSONValue}
     *  instance that represents the given
     *  {@link Integer}
     *  value.
     *
     *  @param  value   The value
     *  @return The JSON value that represents the given value.
     */
    public JSONNumber valueOf( final Integer value );

    /**
     *  Returns a
     *  {@link JSONValue}
     *  instance that represents the given {@code long} value.
     *
     *  @param  value   The value
     *  @return The JSON value that represents the given value.
     */
    public JSONNumber valueOf( final long value );

    /**
     *  Returns a
     *  {@link JSONValue}
     *  instance that represents the given
     *  {@link Long}
     *  value.
     *
     *  @param  value   The value
     *  @return The JSON value that represents the given value.
     */
    public JSONNumber valueOf( final Long value );

    /**
     *  Returns a
     *  {@link JSONValue}
     *  instance that represents the given
     *  {@link String}
     *  value.
     *
     *  @param  value   The value
     *  @return The JSON value that represents the given value.
     */
    public JSONString valueOf( final String value );

    /**
     *  Returns a
     *  {@link JSONValue}
     *  instance that represents the {@code null} value.
     *
     *  @return The JSON value that represents {@code null}.
     */
    public default JSONLiteral valueOfNull() { return NULL; }
}
//  interface JSONBuilder

/*
 *  End of File
 */