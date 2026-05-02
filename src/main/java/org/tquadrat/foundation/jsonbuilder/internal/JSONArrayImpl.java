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

import org.apiguardian.api.API;
import org.tquadrat.foundation.annotation.ClassVersion;
import org.tquadrat.foundation.jsonbuilder.JSONArray;
import org.tquadrat.foundation.jsonbuilder.JSONBuilder;
import org.tquadrat.foundation.jsonbuilder.JSONObject;
import org.tquadrat.foundation.jsonbuilder.JSONValue;
import org.tquadrat.foundation.lang.value.Dimension;
import org.tquadrat.foundation.lang.value.DimensionedValue;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Iterator;
import java.util.List;
import java.util.StringJoiner;

import static java.lang.Integer.max;
import static org.apiguardian.api.API.Status.INTERNAL;
import static org.tquadrat.foundation.jsonbuilder.JSONLiteral.NULL;
import static org.tquadrat.foundation.lang.CommonConstants.EMPTY_STRING;
import static org.tquadrat.foundation.lang.Objects.hash;
import static org.tquadrat.foundation.lang.Objects.requireNonNullArgument;

/**
 *  <p>{@summary The implementation for the interface
 *  {@link JSONArray}.}</p>
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: JSONArrayImpl.java 1195 2026-04-15 21:33:40Z tquadrat $
 *  @since 0.25.0
 *
 *  @UMLGraph.link
 */
@ClassVersion( sourceVersion = "$Id: JSONArrayImpl.java 1195 2026-04-15 21:33:40Z tquadrat $" )
@API( status = INTERNAL, since = "0.25.0" )
public final class JSONArrayImpl implements JSONArray
{
        /*------------*\
    ====** Attributes **=======================================================
        \*------------*/
    /**
     *  The reference to the
     *  {@link JSONBuilder}
     *  that was used to create this {@code JSONObject}, and that is used to
     *  create the members.
     */
    private final JSONBuilderImpl m_Builder;

    /**
     *  The elements for this {@code JSONArray} instance.
     */
    private final List<JSONValue> m_Elements = new ArrayList<>();

        /*--------------*\
    ====** Constructors **=====================================================
        \*--------------*/
    /**
     *  Creates a new instance of {@code JSONArrayImpl}.
     *
     *  @param  builder The reference to the
     *      {@link JSONBuilder}.
     */
    public JSONArrayImpl( final JSONBuilderImpl builder )
    {
        m_Builder = requireNonNullArgument( builder, "builder" );
    }   //  JSONArrayImpl()

    /**
     *  <p>{summary Creates a new instance of {@code JSONArrayImpl} from the
     *  given other array.}</p>
     *  <p>The new array is a deep copy of the given instance.</p>
     *
     *  @param  other   The other JSON array.
     */
    public JSONArrayImpl( final JSONArrayImpl other )
    {
        m_Builder = requireNonNullArgument( other, "other" ).m_Builder;
        for( final var element : other.m_Elements )
        {
            m_Elements.add( switch( element )
            {
                case null -> NULL;
                case JSONArrayImpl array -> new JSONArrayImpl( array );
                case JSONObjectImpl object -> new JSONObjectImpl( object );
                default -> element;
            });
        }
    }   //  JSONArrayImpl()

        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  {@inheritDoc}
     */
    @Override
    public final void add( final BigDecimal value ) { add( m_Builder.valueOf( value ) ); }

    /**
     *  {@inheritDoc}
     */
    @Override
    public final void add( final BigInteger value ) { add( m_Builder.valueOf( value ) ); }

    /**
     *  {@inheritDoc}
     */
    @Override
    public final <T extends Dimension> void add( final DimensionedValue<T> value, final T targetUnit )
    {
        add( m_Builder.valueOf( value, targetUnit ) );
    }   //  add()

    /**
     *  {@inheritDoc}
     */
    @Override
    public final void add( final double value ) { add( m_Builder.valueOf( value ) ); }

    /**
     *  {@inheritDoc}
     */
    @Override
    public final void add( final Double value ) { add( m_Builder.valueOf( value ) ); }

    /**
     *  {@inheritDoc}
     */
    @Override
    public final void add( final float value ) { add( m_Builder.valueOf( value ) ); }

    /**
     *  {@inheritDoc}
     */
    @Override
    public final void add( final Float value ) { add( m_Builder.valueOf( value ) ); }

    /**
     *  {@inheritDoc}
     */
    @Override
    public final void add( final int value ) { add( m_Builder.valueOf( value ) ); }

    /**
     *  {@inheritDoc}
     */
    @Override
    public final void add( final Integer value ) { add( m_Builder.valueOf( value ) ); }

    /**
     *  {@inheritDoc}
     */
    @Override
    public final void add( final JSONValue value )
    {
        m_Elements.add( requireNonNullArgument( value, "value" ) );
    }   //  add()

    /**
     *  {@inheritDoc}
     */
    @Override
    public final void add( final long value ) { add( m_Builder.valueOf( value ) ); }

    /**
     *  {@inheritDoc}
     */
    @Override
    public final void add( final Long value ) { add( m_Builder.valueOf( value ) ); }

    /**
     *  {@inheritDoc}
     */
    @Override
    public final void add( final String value ) { add( m_Builder.valueOf( value ) ); }

    /**
     *  {@inheritDoc}
     */
    @Override
    public final void add( final int index, final BigDecimal value )  throws IndexOutOfBoundsException
    {
        add( index, m_Builder.valueOf( value ) );
    }   //  add()

    /**
     *  {@inheritDoc}
     */
    @Override
    public final void add( final int index, final BigInteger value ) throws IndexOutOfBoundsException
    {
        add( index, m_Builder.valueOf( value ) );
    }   //  add()

    /**
     *  {@inheritDoc}
     */
    @Override
    public final <T extends Dimension> void add( final int index, final DimensionedValue<T> value, final T targetUnit ) throws IndexOutOfBoundsException
    {
        add( index, m_Builder.valueOf( value, targetUnit ) );
    }   //  add()

    /**
     *  {@inheritDoc}
     */
    @Override
    public final void add( final int index, final double value ) throws IndexOutOfBoundsException
    {
        add( index, m_Builder.valueOf( value ) );
    }   //  add()

    /**
     *  {@inheritDoc}
     */
    @Override
    public final void add( final int index, final Double value ) throws IndexOutOfBoundsException
    {
        add( index, m_Builder.valueOf( value ) );
    }   //  add()

    /**
     *  {@inheritDoc}
     */
    @Override
    public final void add( final int index, final float value ) throws IndexOutOfBoundsException
    {
        add( index, m_Builder.valueOf( value ) );
    }   //  add()

    /**
     *  {@inheritDoc}
     */
    @Override
    public final void add( final int index, final Float value ) throws IndexOutOfBoundsException
    {
        add( index, m_Builder.valueOf( value ) );
    }   //  add()

    /**
     *  {@inheritDoc}
     */
    @Override
    public final void add( final int index, final int value ) throws IndexOutOfBoundsException
    {
        add( index, m_Builder.valueOf( value ) );
    }   //  add()

    /**
     *  {@inheritDoc}
     */
    @Override
    public final void add( final int index, final Integer value ) throws IndexOutOfBoundsException
    {
        add( index, m_Builder.valueOf( value ) );
    }   //  add()

    /**
     *  {@inheritDoc}
     */
    @Override
    public final void add( final int index, final JSONValue value ) throws IndexOutOfBoundsException
    {
        m_Elements.add( index, requireNonNullArgument( value, "value" ) );
    }   //  add()

    /**
     *  {@inheritDoc}
     */
    @Override
    public final void add( final int index, final long value ) throws IndexOutOfBoundsException
    {
        add( index, m_Builder.valueOf( value ) );
    }   //  add()

    /**
     *  {@inheritDoc}
     */
    @Override
    public final void add( final int index, final Long value ) throws IndexOutOfBoundsException
    {
        add( index, m_Builder.valueOf( value ) );
    }   //  add()

    /**
     *  {@inheritDoc}
     */
    @Override
    public final void add( final int index, final String value ) throws IndexOutOfBoundsException
    {
        add( index, m_Builder.valueOf( value ) );
    }   //  add()

    /**
     *  {@inheritDoc}
     */
    @Override
    public final boolean addAll( final JSONArray array )
    {
        final var retValue = m_Elements.addAll( ((JSONArrayImpl) requireNonNullArgument( array, "array" )).m_Elements );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  addAll()

    /**
     *  {@inheritDoc}
     */
    @Override
    public final JSONArray addArray()
    {
        final var retValue = m_Builder.createArray();
        add( retValue );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  addArray()

    /**
     *  {@inheritDoc}
     */
    @Override
    public JSONArray addArray( final int index ) throws IndexOutOfBoundsException
    {
        final var retValue = m_Builder.createArray();
        add( index, retValue );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  addArray()

    /**
     *  {@inheritDoc}
     */
    @Override
    public JSONObject addObject()
    {
        final var retValue = m_Builder.createObject();
        add( retValue );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  addObject()

    /**
     *  {@inheritDoc}
     */
    @Override
    public JSONObject addObject( final int index ) throws IndexOutOfBoundsException
    {
        final var retValue = m_Builder.createObject();
        add( index, retValue );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  addObject()

    /**
     *  {@inheritDoc}
     */
    @Override
    public final boolean equals( final Object o )
    {
        var retValue = this == o;
        if( !retValue && o instanceof final JSONArrayImpl other )
        {
            retValue = m_Builder.equals( other.m_Builder )
                && m_Elements.equals( other.m_Elements );
        }

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  equals()

    /**
     * {@inheritDoc}
     */
    @Override
    public void formatTo( final Formatter formatter, final int flags, final int width, final int precision )
    {
        final var indentation1 = "\n" + (width <= 0 ? EMPTY_STRING : " ".repeat( width ));
        final var newWidth = max( 0, width ) + m_Builder.getIndentation();
        final var indentation2 = "\n" + " ".repeat( newWidth );
        final var appendable = formatter.out();
        try
        {
            switch( m_Elements.size() )
            {
                case 0 -> appendable.append( "[]" );
                case 1 ->
                {
                    appendable.append( "[" );
                    final var element = m_Elements.getFirst();
                    element.formatTo( formatter, flags, newWidth, precision );
                    appendable.append( "]" );
                }
                default ->
                {
                    var isFirst = true;
                    appendable.append( "[" );
                    for( final var element : m_Elements )
                    {
                        if( isFirst )
                        {
                            isFirst = false;
                        }
                        else
                        {
                            appendable.append( ',' );
                        }
                        appendable.append( indentation2 );
                        element.formatTo( formatter, flags, newWidth, precision );
                    }
                    appendable.append( indentation1 )
                        .append( "]" );
                }
            }
        }
        catch( final IOException e )
        {
            throw new UncheckedIOException( e.getMessage(), e );
        }
    }   //  formatTo()

    /**
     *  {@inheritDoc}
     */
    @Override
    public final JSONValue get( final int index )  throws IndexOutOfBoundsException
    {
        return m_Elements.get( index );
    }   //  get()

    /**
     *  {@inheritDoc}
     */
    @Override
    public final int hashCode() { return hash( m_Builder, m_Elements ); }

    /**
     *  {@inheritDoc}
     */
    @Override
    public final boolean isEmpty() { return m_Elements.isEmpty(); }

    /**
     *  {@inheritDoc}
     */
    @Override
    public final Iterator<JSONValue> iterator() { return m_Elements.listIterator(); }

    /**
     *  {@inheritDoc}
     */
    @Override
    public final void remove( final int index ) throws IndexOutOfBoundsException
    {
        m_Elements.remove( index );
    }   //  remove()

    /**
     *  {@inheritDoc}
     */
    @Override
    public final void remove( final JSONValue element ) throws IndexOutOfBoundsException
    {
        m_Elements.remove( requireNonNullArgument( element, "element" ) );
    }   //  remove()

    /**
     *  {@inheritDoc}
     */
    @Override
    public final void set( final int index, final BigDecimal value ) throws IndexOutOfBoundsException
    {
        set( index, m_Builder.valueOf( value ) );
    }   //  set()

    /**
     *  {@inheritDoc}
     */
    @Override
    public void set( final int index, final BigInteger value ) throws IndexOutOfBoundsException
    {
        set( index, m_Builder.valueOf( value ) );
    }   //  set()

    /**
     *  {@inheritDoc}
     */
    @Override
    public <T extends Dimension> void set( final int index, final DimensionedValue<T> value, final T targetUnit ) throws IndexOutOfBoundsException
    {
        set( index, m_Builder.valueOf( value, targetUnit ) );
    }   //  set()

    /**
     *  {@inheritDoc}
     */
    @Override
    public void set( final int index, final double value ) throws IndexOutOfBoundsException
    {
        set( index, m_Builder.valueOf( value ) );
    }   //  set()

    /**
     *  {@inheritDoc}
     */
    @Override
    public void set( final int index, final Double value ) throws IndexOutOfBoundsException
    {
        set( index, m_Builder.valueOf( value ) );
    }   //  set()

    /**
     *  {@inheritDoc}
     */
    @Override
    public void set( final int index, final float value ) throws IndexOutOfBoundsException
    {
        set( index, m_Builder.valueOf( value ) );
    }   //  set()

    /**
     *  {@inheritDoc}
     */
    @Override
    public void set( final int index, final Float value ) throws IndexOutOfBoundsException
    {
        set( index, m_Builder.valueOf( value ) );
    }   //  set()

    /**
     *  {@inheritDoc}
     */
    @Override
    public void set( final int index, final int value ) throws IndexOutOfBoundsException
    {
        set( index, m_Builder.valueOf( value ) );
    }   //  set()

    /**
     *  {@inheritDoc}
     */
    @Override
    public void set( final int index, final Integer value ) throws IndexOutOfBoundsException
    {
        set( index, m_Builder.valueOf( value ) );
    }   //  set()

    /**
     *  {@inheritDoc}
     */
    @Override
    public final void set( final int index, final JSONValue value ) throws IndexOutOfBoundsException
    {
        m_Elements.set( index, requireNonNullArgument( value, "value" ) );
    }   //  set()

    /**
     *  {@inheritDoc}
     */
    @Override
    public void set( final int index, final long value ) throws IndexOutOfBoundsException
    {
        set( index, m_Builder.valueOf( value ) );
    }   //  set()

    /**
     *  {@inheritDoc}
     */
    @Override
    public void set( final int index, final Long value ) throws IndexOutOfBoundsException
    {
        set( index, m_Builder.valueOf( value ) );
    }   //  set()

    /**
     *  {@inheritDoc}
     */
    @Override
    public void set( final int index, final String value ) throws IndexOutOfBoundsException
    {
        set( index, m_Builder.valueOf( value ) );
    }   //  set()

    /**
     *  {@inheritDoc}
     */
    @Override
    public final JSONArray setArray( final int index ) throws IndexOutOfBoundsException
    {
        final var retValue = m_Builder.createArray();
        set( index, retValue );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  setArray()

    /**
     *  {@inheritDoc}
     */
    @Override
    public final JSONObject setObject( final int index ) throws IndexOutOfBoundsException
    {
        final var retValue = m_Builder.createObject();
        set( index, retValue );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  setObject()

    /**
     *  {@inheritDoc}
     */
    @Override
    public final int size() { return m_Elements.size(); }

    /**
     *  {@inheritDoc}
     */
    @Override
    public final String toString()
    {
        final var buffer = new StringJoiner( ",", "[", "]" );
        for( final var element : m_Elements )
        {
            buffer.add( element.toString() );
        }
        final var retValue = buffer.toString();

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  toString()
}
//  class JSONArrayImpl

/*
 *  End of File
 */