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

import java.util.Formatter;

import org.apiguardian.api.API;
import org.tquadrat.foundation.annotation.ClassVersion;

/**
 *  <p>{@summary The representation of the JSON literals.} These are</p>
 *  <ul>
 *      <li>{@link #NULL NULL}</li>
 *      <li>{@link #TRUE TRUE}</li>
 *      <li>{@link #FALSE FALSE}</li>
 *  </ul>
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: JSONLiteral.java 1190 2026-04-08 13:27:20Z tquadrat $
 *  @since 0.1.0
 *
 *  @UMLGraph.link
 */
@ClassVersion( sourceVersion = "$Id: JSONLiteral.java 1190 2026-04-08 13:27:20Z tquadrat $" )
@API( status = STABLE, since = "0.1.0" )
public enum JSONLiteral implements JSONValue
{
        /*------------------*\
    ====** Enum Definitions **=================================================
        \*------------------*/
    /**
     *  Represents the JSON literal {@code null}.
     */
    NULL( "null" )
    {
        /**
         *  {@inheritDoc}
         */
        @Override
        public final boolean isNull() { return true; }
    },

    /**
     *  Represents the JSON literal {@code true}.
     */
    TRUE( "true" )
    {
        /**
         *  {@inheritDoc}
         */
        @Override
        public final boolean isTrue() { return true; }
    },

    /**
     *  Represents the JSON literal {@code false}.
     */
    FALSE( "false" )
    {
        /**
         *  {@inheritDoc}
         */
        @Override
        public final boolean isFalse() { return true; }
    };

        /*------------*\
    ====** Attributes **=======================================================
        \*------------*/
    /**
     *  The value for the literal.
     */
    private final String m_Value;

        /*--------------*\
    ====** Constructors **=====================================================
        \*--------------*/
    /**
     *  Creates a new instance of {@code JSONLiteral}.
     *
     *  @param  value   The value.
     */
    private JSONLiteral( final String value )
    {
        m_Value = value;
    }   //  JSONLiteral()

        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     * {@inheritDoc}
     */
    @Override
    public final void formatTo( final Formatter formatter, final int flags, final int width, final int precision )
    {
        formatter.format( m_Value );
    }   //  formatTo()

    /**
     * {@inheritDoc}
     */
    @Override
    public final boolean isBoolean() { return isFalse() || isTrue(); }

    /**
     * {@inheritDoc}
     */
    @Override
    public final String toString() { return m_Value; }
}
//  enum JSONLiteral

/*
 *  End of File
 */