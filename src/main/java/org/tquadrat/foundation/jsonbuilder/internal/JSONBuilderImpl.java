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

package org.tquadrat.foundation.jsonbuilder.internal;

import static java.lang.String.format;
import static java.util.Locale.ROOT;
import static org.apiguardian.api.API.Status.INTERNAL;
import static org.tquadrat.foundation.lang.Objects.requireNonNullArgument;
import static org.tquadrat.foundation.lang.Objects.requireValidIntegerArgument;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Formatter;

import org.apiguardian.api.API;
import org.tquadrat.foundation.annotation.ClassVersion;
import org.tquadrat.foundation.jsonbuilder.JSONArray;
import org.tquadrat.foundation.jsonbuilder.JSONBuilder;
import org.tquadrat.foundation.jsonbuilder.JSONNumber;
import org.tquadrat.foundation.jsonbuilder.JSONObject;
import org.tquadrat.foundation.jsonbuilder.JSONString;
import org.tquadrat.foundation.jsonbuilder.JSONValue;

/**
 *  <p>{@summary The implementation for the interface
 *  {@link JSONBuilder}.}
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: JSONBuilderImpl.java 1190 2026-04-08 13:27:20Z tquadrat $
 *  @since 0.25.0
 *
 *  @UMLGraph.link
 */
@ClassVersion( sourceVersion = "$Id: JSONBuilderImpl.java 1190 2026-04-08 13:27:20Z tquadrat $" )
@API( status = INTERNAL, since = "0.25.0" )
public final class JSONBuilderImpl implements JSONBuilder
{
        /*------------*\
    ====** Attributes **=======================================================
        \*------------*/
    /**
     *  The indentation that is used when a
     *  {@link JSONValue}
     *  is formatted.
     *
     *  @see JSONValue#formatTo(Formatter,int,int,int)
     */
    private int m_Indentation;

        /*--------------*\
    ====** Constructors **=====================================================
        \*--------------*/
    /**
     *  Creates a new instance of {@code JSONBuilder}.
     */
    public JSONBuilderImpl()
    {
        m_Indentation = DEFAULT_INDENTATION;
    }   //  JSONBuilderImpl()

        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  {@inheritDoc}
     */
    @Override
    public final JSONArray createArray() { return new JSONArrayImpl( this ); }

    /**
     *  {@inheritDoc}
     */
    @Override
    public final JSONObject createObject() { return new JSONObjectImpl( this ); }

    /**
     *  {@inheritDoc}
     */
    @Override
    public final int getIndentation() { return m_Indentation; }

    /**
     *  {@inheritDoc}
     */
    @Override
    public final void setIndentation( final int value )
    {
        m_Indentation = requireValidIntegerArgument( value, "value", v -> v > 0 );
    }   //  setIndentation()

    /**
     *  {@inheritDoc}
     */
    @Override
    public JSONNumber valueOf( final BigDecimal value )
    {
        final var retValue = new JSONNumberImpl( format( ROOT, "%f", requireNonNullArgument( value, "value" ) ) );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  valueOf()

    /**
     *  {@inheritDoc}
     */
    @Override
    public JSONNumber valueOf( final BigInteger value )
    {
        final var retValue = new JSONNumberImpl( format( ROOT, "%d", requireNonNullArgument( value, "value" ) ) );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  valueOf()

    /**
     *  {@inheritDoc}
     */
    @Override
    public JSONNumber valueOf( final double value )
    {
        final var retValue = new JSONNumberImpl( Double.toString( value ) );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  valueOf()

    /**
     *  {@inheritDoc}
     */
    @SuppressWarnings( "CallToNumericToString" )
    @Override
    public JSONNumber valueOf( final Double value )
    {
        final var retValue = new JSONNumberImpl( requireNonNullArgument( value, "value" ).toString() );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  valueOf()

    /**
     *  {@inheritDoc}
     */
    @Override
    public JSONNumber valueOf( final float value )
    {
        final var retValue = new JSONNumberImpl( Float.toString( value ) );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  valueOf()

    /**
     *  {@inheritDoc}
     */
    @SuppressWarnings( "CallToNumericToString" )
    @Override
    public JSONNumber valueOf( final Float value )
    {
        final var retValue = new JSONNumberImpl( requireNonNullArgument( value, "value" ).toString() );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  valueOf()

    /**
     *  {@inheritDoc}
     */
    @Override
    public JSONNumber valueOf( final int value )
    {
        final var retValue = new JSONNumberImpl( Integer.toString( value ) );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  valueOf()

    /**
     *  {@inheritDoc}
     */
    @SuppressWarnings( "CallToNumericToString" )
    @Override
    public JSONNumber valueOf( final Integer value )
    {
        final var retValue = new JSONNumberImpl( requireNonNullArgument( value, "value" ).toString() );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  valueOf()

    /**
     *  {@inheritDoc}
     */
    @Override
    public JSONNumber valueOf( final long value )
    {
        final var retValue = new JSONNumberImpl( Long.toString( value ) );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  valueOf()

    /**
     *  {@inheritDoc}
     */
    @SuppressWarnings( "CallToNumericToString" )
    @Override
    public JSONNumber valueOf( final Long value )
    {
        final var retValue = new JSONNumberImpl( requireNonNullArgument( value, "value" ).toString() );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  valueOf()

    /**
     *  {@inheritDoc}
     */
    @Override
    public JSONString valueOf( final String value )
    {
        final var retValue = new JSONStringImpl( requireNonNullArgument( value, "value" ) );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  valueOf()
}
//  class JSONBuilderImpl

/*
 *  End of File
 */