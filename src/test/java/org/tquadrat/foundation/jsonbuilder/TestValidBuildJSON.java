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

import com.eclipsesource.json.JsonParser;
import com.eclipsesource.json.JsonParser_Test.TestHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.tquadrat.foundation.annotation.ClassVersion;
import org.tquadrat.foundation.testutil.TestBaseClass;

import java.time.DayOfWeek;
import java.time.Month;
import java.util.stream.Stream;
import java.util.stream.Stream.Builder;

import static java.lang.String.format;
import static java.time.format.TextStyle.FULL_STANDALONE;
import static java.util.Arrays.stream;
import static java.util.Locale.GERMAN;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

/**
 *  Some tests that should confirm that we will generate valid JSON.
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: TestFormattedOutput.java 1190 2026-04-08 13:27:20Z tquadrat $
 */
@ClassVersion( sourceVersion = "$Id: TestFormattedOutput.java 1190 2026-04-08 13:27:20Z tquadrat $" )
@DisplayName( "org.tquadrat.foundation.jsonbuilder.TestValidBuildJSON" )
public class TestValidBuildJSON extends TestBaseClass
{
        /*------------*\
    ====** Attributes **=======================================================
        \*------------*/
    /**
     *  The builder that is used to create the JSON data.
     */
    private static final JSONBuilder m_Builder = JSONBuilder.getInstance();

    /**
     *  The handler for the parsing.
     */
    @SuppressWarnings( "FieldCanBeLocal" )
    private TestHandler m_Handler;

    /**
     *  The JSON parser.
     */
    private JsonParser m_Parser;

        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  The housekeeper.
     */
    @BeforeEach
    public final void beforeEach()
    {
        m_Handler = new TestHandler();
        m_Parser = new JsonParser( m_Handler );
    }   //  beforeEach()

    /**
     *  Creates a half-way complex JSON data structure.
     *
     *  @return A JSON value.
     */
    public static final Stream<? extends JSONValue> createJSON()
    {
        final Builder<JSONValue> stream = Stream.builder();
        JSONObject root = m_Builder.createObject();
        stream.add( root );

        root = m_Builder.copyObject( root );
        JSONArray array = root.setArray( "Array" );
        JSONObject object = root.setObject( "Object" );
        stream.add( m_Builder.copyObject( root ) );

        root.set( "String", "This is a String" );
        root.set( "Number", 123456L );
        stream.add( m_Builder.copyObject( root ) );

        object.set( "String", "This is a String" );
        object.set( "Number", 123456L );
        stream.add( m_Builder.copyObject( root ) );

        object.set( "StringArray", m_Builder.createArray( "eins", "zwei", "drei", "vier", "fünf" ) );
        root.set( "Month", m_Builder.createObject( stream( Month.values() ).map( Month::name ).toList(), name -> m_Builder.valueOf( Month.valueOf( name ).getDisplayName( FULL_STANDALONE, GERMAN ) ) ) );
        stream.add( m_Builder.copyObject( root ) );

        array.addAll( m_Builder.createArray( stream( DayOfWeek.values() )
            .map( day -> day.getDisplayName( FULL_STANDALONE, GERMAN ) )
            .toArray( String []::new ) ) );
        stream.add( m_Builder.copyObject( root ) );

        final var retValue = stream.build();

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  createJSON()

    /**
     *  Some Tests.
     *
     *  @throws Exception   Something went awfully wrong.
     */
    @ParameterizedTest
    @MethodSource( "createJSON" )
    final void testPlain( final JSONValue jsonValue ) throws Exception
    {
        skipThreadTest();

        assertDoesNotThrow( () -> m_Parser.parse( jsonValue.toString() ) );
    }   //  testPlain()

    /**
     *  Some Tests.
     *
     *  @throws Exception   Something went awfully wrong.
     */
    @ParameterizedTest
    @MethodSource( "createJSON" )
    final void testPrettyPrinted( final JSONValue jsonValue ) throws Exception
    {
        skipThreadTest();

        assertDoesNotThrow( () -> m_Parser.parse( format( "%s", jsonValue ) ) );
    }   //  testPrettyPrinted()
}
//  class TestValidBuildJSON

/*
 *  End of File
 */