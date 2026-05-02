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

import java.math.BigDecimal;
import java.math.BigInteger;

import org.apiguardian.api.API;
import org.tquadrat.foundation.annotation.ClassVersion;
import org.tquadrat.foundation.jsonbuilder.internal.JSONNumberImpl;

/**
 *  <p>{@summary The definition of a JSON number.}</p>>
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: JSONNumber.java 1187 2026-04-07 11:01:35Z tquadrat $
 *  @since 0.25.0
 *
 *  @UMLGraph.link
 */
@ClassVersion( sourceVersion = "$Id: JSONNumber.java 1187 2026-04-07 11:01:35Z tquadrat $" )
@API( status = STABLE, since = "0.25.0" )
public sealed interface JSONNumber extends JSONValue
    permits JSONNumberImpl
{
        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  <p>{@summary Returns the value as an
     *  {@link BigDecimal}.}</p>
     *
     *  @return The value.
     *  @throws NumberFormatException   The value cannot be parsed to a valid
     *      {@link BigDecimal}.
     */
    public BigDecimal getBigDecimal() throws NumberFormatException;

    /**
     *  <p>{@summary Returns the value as an
     *  {@link BigInteger}.}</p>
     *
     *  @return The value.
     *  @throws NumberFormatException   The value cannot be parsed to a valid
     *      {@link BigInteger}.
     */
    public BigInteger getBigInteger() throws NumberFormatException;

    /**
     *  <p>{@summary Returns the value as an {@code double}.}</p>
     *
     *  @return The value.
     *  @throws NumberFormatException   The value cannot be parsed to a valid
     *      {@code double}.
     */
    public double getDouble() throws NumberFormatException;

    /**
     *  <p>{@summary Returns the value as an {@code float}.}</p>
     *
     *  @return The value.
     *  @throws NumberFormatException   The value cannot be parsed to a valid
     *      {@code float}.
     */
    public float getFloat() throws NumberFormatException;

    /**
     *  <p>{@summary Returns the value as an {@code int}.}</p>
     *
     *  @return The value.
     *  @throws NumberFormatException   The value cannot be parsed to a valid
     *      {@code int}.
     */
    public int getInt() throws NumberFormatException;

    /**
     *  <p>{@summary Returns the value as an {@code long}.}</p>
     *
     *  @return The value.
     *  @throws NumberFormatException   The value cannot be parsed to a valid
     *      {@code long}.
     */
    public long getLong() throws NumberFormatException;
}
//  interface JSONNumber

/*
 *  End of File
 */