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
import java.util.Formatter;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Optional;
import java.util.SequencedCollection;
import java.util.SequencedMap;
import java.util.StringJoiner;

import static java.lang.Integer.max;
import static java.util.Collections.unmodifiableSequencedCollection;
import static org.apiguardian.api.API.Status.INTERNAL;
import static org.tquadrat.foundation.jsonbuilder.JSONLiteral.NULL;
import static org.tquadrat.foundation.lang.CommonConstants.EMPTY_STRING;
import static org.tquadrat.foundation.lang.Objects.hash;
import static org.tquadrat.foundation.lang.Objects.requireNonNullArgument;
import static org.tquadrat.foundation.lang.Objects.requireNotBlankArgument;

/**
 *  <p>{@summary The implementation for the interface
 *  {@link JSONObject}.}</p>
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: JSONObjectImpl.java 1195 2026-04-15 21:33:40Z tquadrat $
 *  @since 0.25.0
 *
 *  @UMLGraph.link
 */
@ClassVersion( sourceVersion = "$Id: JSONObjectImpl.java 1195 2026-04-15 21:33:40Z tquadrat $" )
@API( status = INTERNAL, since = "0.25.0" )
public final class JSONObjectImpl implements JSONObject
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
     *  The members of this {@code JSONObject}.
     */
    private final SequencedMap<String,JSONValue> m_Members = new LinkedHashMap<>();

        /*--------------*\
    ====** Constructors **=====================================================
        \*--------------*/
    /**
     *  Creates a new instance of {@code JSONObjectImpl}.
     *
     *  @param  builder The reference to the
     *      {@link JSONBuilder}.
     */
    public JSONObjectImpl( final JSONBuilderImpl builder )
    {
        m_Builder = requireNonNullArgument( builder, "builder" );
    }   //  JSONObjectImpl()

    /**
     *  <p>{summary Creates a new instance of {@code JSONObjectImpl} from the
     *  given other object.}</p>
     *  <p>The new object is a deep copy of the given instance.</p>
     *
     *  @param  other   The other JSON object.
     */
    public JSONObjectImpl( final JSONObjectImpl other )
    {
        m_Builder = requireNonNullArgument( other, "other" ).m_Builder;
        for( final var entry : other.m_Members.entrySet() )
        {
            final var name = entry.getKey();
            final var value = entry.getValue();
            m_Members.put( name, switch( value )
            {
               case null -> NULL;
               case JSONArrayImpl array -> new JSONArrayImpl( array );
               case JSONObjectImpl object -> new JSONObjectImpl( object );
               default -> value;
            });
        }
    }   //  JSONObjectImpl()

        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  {@inheritDoc}
     */
    @Override
    public final boolean contains( final String name ) { return m_Members.containsKey( name ); }

    /**
     *  {@inheritDoc}
     */
    @Override
    public final boolean equals( final Object o )
    {
        var retValue = this == o;
        if( !retValue && o instanceof final JSONObjectImpl other )
        {
            retValue = m_Builder.equals( other.m_Builder )
                && m_Members.equals( other.m_Members );
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
            switch( m_Members.size() )
            {
                case 0 -> appendable.append( "{}" );
                case 1 ->
                {
                    final var entry = m_Members.firstEntry();
                    appendable.append( "{ " )
                        .append( '"' )
                        .append( entry.getKey() )
                        .append( '"' )
                        .append( " : " );
                    entry.getValue().formatTo( formatter, flags, newWidth, precision );
                    appendable.append( " }" );
                }
                default ->
                {
                    var isFirst = true;
                    appendable.append( "{" );
                    for( final var entry : m_Members.entrySet() )
                    {
                        if( isFirst )
                        {
                            isFirst = false;
                        }
                        else
                        {
                            appendable.append( ',' );
                        }
                        appendable.append( indentation2 )
                            .append( '"' )
                            .append( entry.getKey() )
                            .append( '"' )
                            .append( " : " );
                        entry.getValue().formatTo( formatter, flags, newWidth, precision );
                    }
                    appendable.append( indentation1 )
                        .append( "}" );
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
    public final Optional<JSONValue> get( final String name )
    {
        final var retValue = Optional.ofNullable( m_Members.get( name ) );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  get()

    /**
     *  {@inheritDoc}
     */
    @Override
    public final int hashCode() { return hash( m_Builder, m_Members ); }

    /**
     *  {@inheritDoc}
     */
    @Override
    public final boolean isEmpty() { return m_Members.isEmpty(); }

    /**
     *  {@inheritDoc}
     */
    @Override
    public final Iterator<JSONValue> iterator() { return m_Members.sequencedValues().iterator(); }

    /**
     *  {@inheritDoc}
     */
    @Override
    public JSONObject merge( final JSONObject object )
    {
        m_Members.putAll( ((JSONObjectImpl) requireNonNullArgument( object, "object" )).m_Members );

        //---* Done *----------------------------------------------------------
        return this;
    }   //  merge()

    /**
     *  {@inheritDoc}
     */
    @Override
    public final SequencedCollection<String> names()
    {
        final var retValue = unmodifiableSequencedCollection( m_Members.sequencedKeySet() );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  names()

    /**
     *  {@inheritDoc}
     */
    @Override
    public final JSONObject remove( final String name )
    {
        m_Members.remove( name );

        //---* Done *----------------------------------------------------------
        return this;
    }   //  remove()

    /**
     *  {@inheritDoc}
     */
    @Override
    public final JSONObject set( final String name, final BigDecimal value )
    {
        return set( name, m_Builder.valueOf( value ) );
    }   //  set

    /**
     *  {@inheritDoc}
     */
    @Override
    public final JSONObject set( final String name, final BigInteger value )
    {
        return set( name, m_Builder.valueOf( value ) );
    }   //  set

    /**
     *  {@inheritDoc}
     */
    @Override
    public <T extends Dimension> JSONObject set( final String name, final DimensionedValue<T> value, final T targetUnit )
    {
        return set( name, m_Builder.valueOf( value, targetUnit ) );
    }   //  set()

    /**
     *  {@inheritDoc}
     */
    @Override
    public final JSONObject set( final String name, final double value )
    {
        return set( name, m_Builder.valueOf( value ) );
    }   //  set

    /**
     *  {@inheritDoc}
     */
    @Override
    public final JSONObject set( final String name, final Double value )
    {
        return set( name, m_Builder.valueOf( value ) );
    }   //  set

    /**
     *  {@inheritDoc}
     */
    @Override
    public final JSONObject set( final String name, final float value )
    {
        return set( name, m_Builder.valueOf( value ) );
    }   //  set

    /**
     *  {@inheritDoc}
     */
    @Override
    public final JSONObject set( final String name, final Float value )
    {
        return set( name, m_Builder.valueOf( value ) );
    }   //  set

    /**
     *  {@inheritDoc}
     */
    @Override
    public final JSONObject set( final String name, final int value )
    {
        return set( name, m_Builder.valueOf( value ) );
    }   //  set

    /**
     *  {@inheritDoc}
     */
    @Override
    public final JSONObject set( final String name, final Integer value )
    {
        return set( name, m_Builder.valueOf( value ) );
    }   //  set

    /**
     *  {@inheritDoc}
     */
    @Override
    public final JSONObject set( final String name, final JSONValue value )
    {
        m_Members.put( requireNotBlankArgument( name, "name" ), requireNonNullArgument( value, "value" ) );

        //---* Done *----------------------------------------------------------
        return this;
    }   //  set

    /**
     *  {@inheritDoc}
     */
    @Override
    public final JSONObject set( final String name, final long value )
    {
        return set( name, m_Builder.valueOf( value ) );
    }   //  set

    /**
     *  {@inheritDoc}
     */
    @Override
    public final JSONObject set( final String name, final Long value )
    {
        return set( name, m_Builder.valueOf( value ) );
    }   //  set

    /**
     *  {@inheritDoc}
     */
    @Override
    public final JSONObject set( final String name, final String value )
    {
        return set( name, m_Builder.valueOf( value ) );
    }   //  set

    /**
     *  {@inheritDoc}
     */
    @Override
    public final JSONArray setArray( final String name )
    {
        final var retValue = m_Builder.createArray();
        set( name, retValue );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  setArray()

    /**
     *  {@inheritDoc}
     */
    @Override
    public final JSONObject setObject( final String name )
    {
        final var retValue = m_Builder.createObject();
        set( name, retValue );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  setArray()

    /**
     *  {@inheritDoc}
     */
    @Override
    public final int size() { return m_Members.size(); }

    /**
     *  {@inheritDoc}
     */
    @Override
    public final String toString()
    {
        final var buffer = new StringJoiner( ",", "{", "}" );
        for( final var entry : m_Members.entrySet() )
        {
            buffer.add( "%s:%s".formatted( new JSONStringImpl( entry.getKey() ).toString(), entry.getValue().toString() ) );
        }
        final var retValue = buffer.toString();

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  toString()
}
//  class JSONObjectImpl

/*
 *  End of File
 */