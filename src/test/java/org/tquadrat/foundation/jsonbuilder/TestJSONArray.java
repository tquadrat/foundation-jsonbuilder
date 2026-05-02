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

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.tquadrat.foundation.annotation.ClassVersion;
import org.tquadrat.foundation.exception.NullArgumentException;
import org.tquadrat.foundation.testutil.TestBaseClass;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 *  Some tests for the interface
 *  {@link JSONArray}
 *  and the implementation class
 *  {@link org.tquadrat.foundation.jsonbuilder.internal.JSONArrayImpl}.
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: TestFormattedOutput.java 1190 2026-04-08 13:27:20Z tquadrat $
 */
@ClassVersion( sourceVersion = "$Id: TestFormattedOutput.java 1190 2026-04-08 13:27:20Z tquadrat $" )
@DisplayName( "org.tquadrat.foundation.jsonbuilder.TestJSONArray" )
public class TestJSONArray extends TestBaseClass
{
        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  Tests for
     *  {@link JSONArray#set(int, JSONValue)}.
     *
     *  @throws Exception   Something went awfully wrong.
     */
    @Test
    final void testSet() throws Exception
    {
        skipThreadTest();

        final var builder = assertDoesNotThrow( JSONBuilder::getInstance );
        assertNotNull( builder );
        final var candidate = assertDoesNotThrow( () -> builder.createArray() );
        assertNotNull( candidate );

        assertEquals( 0, candidate.size() );
        assertTrue( candidate.isEmpty() );

        assertThrows( IndexOutOfBoundsException.class, () -> candidate.set( 0, 1 ) );
        assertEquals( 0, candidate.size() );
        assertTrue( candidate.isEmpty() );

        assertThrows( IndexOutOfBoundsException.class, () -> candidate.add( 1, 1 ) );
        assertEquals( 0, candidate.size() );
        assertTrue( candidate.isEmpty() );

        assertDoesNotThrow( () -> candidate.add( candidate.size(), 1 ) );
        assertEquals( 1, candidate.size() );
        assertFalse( candidate.isEmpty() );
        assertEquals( 1, candidate.getInt( candidate.size() - 1 ) );

        assertDoesNotThrow( () -> candidate.set( candidate.size() - 1, 2 ) );
        assertEquals( 1, candidate.size() );
        assertFalse( candidate.isEmpty() );
        assertEquals( 2, candidate.getInt( candidate.size() - 1 ) );

        JSONValue value = null;
        assertThrows( NullArgumentException.class, () -> candidate.add( value ) );
        assertThrows( NullArgumentException.class, () -> candidate.add( 0, value ) );
        assertThrows( NullArgumentException.class, () -> candidate.set( 0, value ) );
    }   //  class testSet()
}
//  class TestJSONArray

/*
 *  End of File
 */