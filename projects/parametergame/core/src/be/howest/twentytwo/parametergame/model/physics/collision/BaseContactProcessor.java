package be.howest.twentytwo.parametergame.model.physics.collision;

import java.util.Collection;

import be.howest.twentytwo.parametergame.model.event.EventQueue;
import be.howest.twentytwo.parametergame.model.physics.message.IPhysicsMessage;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;

/**
 * Classes that implement this handle specific physics collision interactions.
 */
public abstract class BaseContactProcessor implements ContactListener {

	private ContactListener next;
	
	private final EventQueue eventQueue;
	private final Collection<IPhysicsMessage> messageQueue;

	public BaseContactProcessor(ContactListener next, EventQueue eventQueue, Collection<IPhysicsMessage> events) {
		setNextProcessor(next);
		this.eventQueue = eventQueue;
		this.messageQueue = events;
	}

	public BaseContactProcessor(EventQueue eventQueue, Collection<IPhysicsMessage> events) {
		this(new NullContactProcessor(), eventQueue, events);
	}

	/**
	 * Sets the given {@link ContactListener} as the link following this chain.
	 * Note that if this object had any previous links, they are discarded!
	 */
	public void setNextProcessor(ContactListener next) {
		this.next = next;
	}

	/**
	 * Inserts the given processor after this contact processor and appends the
	 * existing processors behind it.
	 * 
	 * Note that this replaces any processors previously chained to the given
	 * processor.
	 * 
	 * Returns this ContactProcessor for chaining.
	 */
	public BaseContactProcessor insertProcessor(BaseContactProcessor processor) {
		processor.setNextProcessor(this.next);
		setNextProcessor(processor);
		return this;
	}
	
	/**
	 * Alias for {@link #insertProcessor(BaseContactProcessor)}.
	 */
	public BaseContactProcessor addProcessor(BaseContactProcessor processor){
		return insertProcessor(processor);
	}

	/**
	 * Calls the concrete {@link #handleBeginContact(Contact)} implementation.
	 * If it does not handle the collision, it is passed onto the next Processor
	 * in the chain.
	 */
	@Override
	public void beginContact(Contact contact) {
		if (!handleBeginContact(contact) && next != null) {
			next.beginContact(contact);
		}
	}

	/**
	 * Calls the concrete {@link #handleEndContact(Contact)} implementation. If
	 * it does not handle the collision, it is passed onto the next Processor in
	 * the chain.
	 */
	@Override
	public void endContact(Contact contact) {
		if (!handleEndContact(contact) && next != null) {
			next.endContact(contact);
		}
	}

	/**
	 * Calls the concrete {@link #preSolve(Contact, Manifold)} implementation.
	 * If it does not handle the collision, it is passed onto the next Processor
	 * in the chain.
	 */
	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		if (!handlePreSolve(contact, oldManifold) && next != null) {
			next.preSolve(contact, oldManifold);
		}
	}

	/**
	 * Calls the concrete {@link #postSolve(Contact, ContactImpulse)}
	 * implementation. If it does not handle the collision, it is passed onto
	 * the next Processor in the chain.
	 */
	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		if (!handlePostSolve(contact, impulse) && next != null) {
			next.postSolve(contact, impulse);
		}
	}

	/**
	 * Attempt to handle the specific contact event. Return true if handled
	 * successfully, false otherwise.
	 */
	protected abstract boolean handleBeginContact(Contact contact);

	/**
	 * Attempt to handle the specific contact event. Return true if handled
	 * successfully, false otherwise.
	 */
	protected abstract boolean handleEndContact(Contact contact);

	/**
	 * Attempt to handle the specific contact event. Return true if handled
	 * successfully, false otherwise.
	 */
	protected abstract boolean handlePreSolve(Contact contact, Manifold oldManifold);

	/**
	 * Attempt to handle the specific contact event. Return true if handled
	 * successfully, false otherwise.
	 */
	protected abstract boolean handlePostSolve(Contact contact, ContactImpulse impulse);
	
	protected Collection<IPhysicsMessage> getPhysicsQueue(){
		return this.messageQueue;
	}
	
	protected EventQueue getEventQueue(){
		return this.eventQueue;
	}
}
