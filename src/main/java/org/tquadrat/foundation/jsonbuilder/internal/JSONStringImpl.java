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
import static org.tquadrat.foundation.lang.Objects.requireNonNullArgument;
import static org.tquadrat.foundation.util.StringUtils.escapeJSON;

import java.util.Formatter;

import org.apiguardian.api.API;
import org.tquadrat.foundation.annotation.ClassVersion;
import org.tquadrat.foundation.jsonbuilder.JSONNumber;
import org.tquadrat.foundation.jsonbuilder.JSONString;

/**
 *  <p>{@summary The implementation of the interface
 *  {@link JSONString}.}</p>
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: JSONStringImpl.java 1190 2026-04-08 13:27:20Z tquadrat $
 *  @since 0.25.0
 *
 *  @UMLGraph.link
 */
@ClassVersion( sourceVersion = "$Id: JSONStringImpl.java 1190 2026-04-08 13:27:20Z tquadrat $" )
@API( status = INTERNAL, since = "0.25.0" )
public final class JSONStringImpl implements JSONString
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
    public JSONStringImpl( final String value )
    {
        m_Value = requireNonNullArgument( value, "value" );
    }   //  JSONStringImpl()

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
        if( !retValue && o instanceof final JSONStringImpl other )
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
        formatter.format( toString() );
    }   //  formatTo()

    /**
     *  {@inheritDoc}
     */
    @Override
    public final String getString() { return m_Value; }

    /**
     *  {@inheritDoc}
     */
    @Override
    public int hashCode() { return hash( m_Value ); }

    /**
     *  {@inheritDoc}
     */
    @Override
    public String toString() { return escapeJSON( m_Value ); }
}
//  class JSONStringImpl

/*
 *  End of File
 */