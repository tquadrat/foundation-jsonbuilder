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

import static org.apiguardian.api.API.Status.INTERNAL;
import static org.tquadrat.foundation.lang.Objects.hash;
import static org.tquadrat.foundation.lang.Objects.requireNotBlankArgument;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Formatter;

import org.apiguardian.api.API;
import org.tquadrat.foundation.annotation.ClassVersion;
import org.tquadrat.foundation.exception.ValidationException;
import org.tquadrat.foundation.jsonbuilder.JSONNumber;

/**
 *  <p>{@summary The implementation of the interface
 *  {@link JSONNumber}.}</p>
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: JSONNumberImpl.java 1190 2026-04-08 13:27:20Z tquadrat $
 *  @since 0.25.0
 *
 *  @UMLGraph.link
 */
@ClassVersion( sourceVersion = "$Id: JSONNumberImpl.java 1190 2026-04-08 13:27:20Z tquadrat $" )
@API( status = INTERNAL, since = "0.25.0" )
public final class JSONNumberImpl implements JSONNumber
{
        /*------------*\
    ====** Attributes **=======================================================
        \*------------*/
    /**
     *  The value.
     */
    private final String m_Value;

        /*--------------*\
    ====** Constructors **=====================================================
        \*--------------*/
    /**
     *  Creates a new instance of {@link JSONNumber}.
     *
     *  @param  value   The value.
     */
    public JSONNumberImpl( final String value )
    {
        m_Value = validateNumber( value );
    }   //  JSONNumberImpl()

        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  {@inheritDoc}
     */
    @Override
    public final boolean equals( final Object o )
    {
        var retValue = this == o;
        if( !retValue && o instanceof final JSONNumberImpl other )
        {
            retValue = m_Value.equals( other.m_Value );
        }
        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  equals()

    /**
     * {@inheritDoc}
     */
    @Override
    public final void formatTo( final Formatter formatter, final int flags, final int width, final int precision )
    {
        formatter.format( m_Value );
    }   //  formatTo()

    /**
     *  {@inheritDoc}
     */
    @Override
    public final BigDecimal getBigDecimal() throws NumberFormatException
    {
        final var retValue = new BigDecimal( m_Value );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  getBigDecimal()

    /**
     *  {@inheritDoc}
     */
    @Override
    public final BigInteger getBigInteger() throws NumberFormatException
    {
        final var retValue = new BigInteger( m_Value );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  getBigInteger()

    /**
     *  {@inheritDoc}
     */
    @Override
    public final double getDouble() throws NumberFormatException
    {
        final var retValue = Double.parseDouble( m_Value );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  getDouble()

    /**
     *  {@inheritDoc}
     */
    @Override
    public final float getFloat() throws NumberFormatException
    {
        final var retValue = Float.parseFloat( m_Value );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  getFloat()

    /**
     *  {@inheritDoc}
     */
    @Override
    public final int getInt() throws NumberFormatException
    {
        final var retValue = Integer.parseInt( m_Value );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  getInt()

    /**
     *  {@inheritDoc}
     */
    @Override
    public final long getLong() throws NumberFormatException
    {
        final var retValue = Long.parseLong( m_Value );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  getLong()

    /**
     *  {@inheritDoc}
     */
    @Override
    public int hashCode() { return hash( m_Value ); }

    /**
     *  Validates whether the given value is a valid numerical value.
     *
     *  @param  value   The value.
     *  @return The given value.
     *  @throws ValidationException The given value is not a valid number.
     */
    private static final String validateNumber( final String value )
    {
        try
        {
            //noinspection ResultOfObjectAllocationIgnored
            new BigDecimal( requireNotBlankArgument( value, "value" ) );
        }
        catch( final NumberFormatException e )
        {
            throw new ValidationException( "The given value '%s' is not a valid number".formatted( value ), e );
        }

        //---* Done *----------------------------------------------------------
        return value;
    }   //  validateNumber()

    /**
     * {@inheritDoc}
     */
    @Override
    public final String toString() { return m_Value; }
}
//  class JSONNumberImpl

/*
 *  End of File
 */