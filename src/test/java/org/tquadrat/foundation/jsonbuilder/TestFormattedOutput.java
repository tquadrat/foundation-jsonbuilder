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

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.tquadrat.foundation.annotation.ClassVersion;
import org.tquadrat.foundation.testutil.TestBaseClass;

/**
 *  Some tests for the formatted output.
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: TestFormattedOutput.java 1190 2026-04-08 13:27:20Z tquadrat $
 */
@ClassVersion( sourceVersion = "$Id: TestFormattedOutput.java 1190 2026-04-08 13:27:20Z tquadrat $" )
@DisplayName( "org.tquadrat.foundation.jsonbuilder.TestFormattedOutput" )
public class TestFormattedOutput extends TestBaseClass
{
        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  Some tests for the formatted output.
     *
     *  @throws Exception   Something went awfully wrong.
     */
    @Test
    final void testFormattedOutput() throws Exception
    {
        skipThreadTest();

        final var builder = JSONBuilder.getInstance();

        final var candidate = builder.createObject();
        assertEquals( "{}", candidate.toString() );
        assertEquals( "{}", "%s".formatted( candidate.toString() ) );
        assertEquals( "{}", "%2s".formatted( candidate.toString() ) );

        candidate.set( "booleanValue", true );
        assertEquals( """
            {"booleanValue":true}\
            """, candidate.toString() );
        assertEquals(  """
            { "booleanValue" : true }\
            """, "%s".formatted( candidate ) );
        assertEquals(  """
            { "booleanValue" : true }\
            """, "%2s".formatted( candidate ) );

        candidate.set( "integerValue", 123 );
        assertEquals( """
            {"booleanValue":true,"integerValue":123}\
            """, candidate.toString() );
        assertEquals(  """
            {
              "booleanValue" : true,
              "integerValue" : 123
            }\
            """, "%s".formatted( candidate ) );
        assertEquals(  """
            {
                "booleanValue" : true,
                "integerValue" : 123
              }\
            """, "%2s".formatted( candidate ) );

        candidate.set( "stringValue", "simpleString" );
        assertEquals( """
            {"booleanValue":true,"integerValue":123,"stringValue":"simpleString"}\
            """, candidate.toString() );
        assertEquals(  """
            {
              "booleanValue" : true,
              "integerValue" : 123,
              "stringValue" : "simpleString"
            }\
            """, "%s".formatted( candidate ) );

        candidate.set( "stringValue", """
            FirstLine
            SecondLine
            ThirdLine""" );
        assertEquals( """
            {"booleanValue":true,"integerValue":123,"stringValue":"FirstLine\\nSecondLine\\nThirdLine"}\
            """, candidate.toString() );
        assertEquals(  """
            {
              "booleanValue" : true,
              "integerValue" : 123,
              "stringValue" : "FirstLine\\nSecondLine\\nThirdLine"
            }\
            """, "%s".formatted( candidate ) );

        final var object = candidate.setObject( "objectValue" );
        assertEquals( """
            {"booleanValue":true,"integerValue":123,"stringValue":"FirstLine\\nSecondLine\\nThirdLine",\
            "objectValue":{}}\
            """, candidate.toString() );
        assertEquals(  """
            {
              "booleanValue" : true,
              "integerValue" : 123,
              "stringValue" : "FirstLine\\nSecondLine\\nThirdLine",
              "objectValue" : {}
            }\
            """, "%s".formatted( candidate ) );

        object.set( "doubleValue", 3.14d );
        assertEquals( """
            {"booleanValue":true,"integerValue":123,"stringValue":"FirstLine\\nSecondLine\\nThirdLine",\
            "objectValue":{"doubleValue":3.14}}\
            """, candidate.toString() );
        assertEquals(  """
            {
              "booleanValue" : true,
              "integerValue" : 123,
              "stringValue" : "FirstLine\\nSecondLine\\nThirdLine",
              "objectValue" : { "doubleValue" : 3.14 }
            }\
            """, "%s".formatted( candidate ) );

        final var array = object.setArray( "arrayValue" );
        assertEquals( """
            {"booleanValue":true,"integerValue":123,"stringValue":"FirstLine\\nSecondLine\\nThirdLine",\
            "objectValue":{"doubleValue":3.14,"arrayValue":[]}}\
            """, candidate.toString() );
        assertEquals(  """
            {
              "booleanValue" : true,
              "integerValue" : 123,
              "stringValue" : "FirstLine\\nSecondLine\\nThirdLine",
              "objectValue" : {
                "doubleValue" : 3.14,
                "arrayValue" : []
              }
            }\
            """, "%s".formatted( candidate ) );

        array.add( 44.55);
        assertEquals( """
            {"booleanValue":true,"integerValue":123,"stringValue":"FirstLine\\nSecondLine\\nThirdLine",\
            "objectValue":{"doubleValue":3.14,"arrayValue":[44.55]}}\
            """, candidate.toString() );
        assertEquals(  """
            {
              "booleanValue" : true,
              "integerValue" : 123,
              "stringValue" : "FirstLine\\nSecondLine\\nThirdLine",
              "objectValue" : {
                "doubleValue" : 3.14,
                "arrayValue" : [44.55]
              }
            }\
            """, "%s".formatted( candidate ) );

        array.add( "Fußpilz" );
        array.add( false );
        array.addObject().set( "stringValue", "simpleString" ).set( "booleanValue", false );
        assertEquals( """
            {"booleanValue":true,"integerValue":123,"stringValue":"FirstLine\\nSecondLine\\nThirdLine",\
            "objectValue":{"doubleValue":3.14,"arrayValue":[44.55,"Fußpilz",\
            false,{"stringValue":"simpleString","booleanValue":false}]}}\
            """, candidate.toString() );
        assertEquals(  """
            {
              "booleanValue" : true,
              "integerValue" : 123,
              "stringValue" : "FirstLine\\nSecondLine\\nThirdLine",
              "objectValue" : {
                "doubleValue" : 3.14,
                "arrayValue" : [
                  44.55,
                  "Fußpilz",
                  false,
                  {
                    "stringValue" : "simpleString",
                    "booleanValue" : false
                  }
                ]
              }
            }\
            """, "%s".formatted( candidate ) );
    }   //  testFormattedOutput
}
//  class TestFormattedOutput

/*
 *  End of File
 */