package be.howest.twentytwo.parametergame.model.physics.collision;

import java.util.Collection;

import be.howest.twentytwo.parametergame.dataTypes.WeaponDataI;
import be.howest.twentytwo.parametergame.model.event.EventQueue;
import be.howest.twentytwo.parametergame.model.event.collision.EnemyHitEvent;
import be.howest.twentytwo.parametergame.model.event.collision.PlayerHitEvent;
import be.howest.twentytwo.parametergame.model.event.collision.PlayerPickupEvent;
import be.howest.twentytwo.parametergame.model.event.game.DestroyEntityEvent;
import be.howest.twentytwo.parametergame.model.physics.message.ExplosionPhysicsMessage;
import be.howest.twentytwo.parametergame.model.physics.message.IPhysicsMessage;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

public class PlayerContactProcessor extends BaseContactProcessor {

	public PlayerContactProcessor(ContactListener next, EventQueue eventQueue,
			Collection<IPhysicsMessage> physicsMessages) {
		super(next, eventQueue, physicsMessages);
	}

	public PlayerContactProcessor(EventQueue eventQueue, Collection<IPhysicsMessage> events) {
		this(new NullContactProcessor(), eventQueue, events);
	}

	@Override
	protected boolean handleBeginContact(Contact contact) {
		short categoryA = contact.getFixtureA().getFilterData().categoryBits;
		short categoryB = contact.getFixtureB().getFilterData().categoryBits;
		if(categoryA == Collision.PLAYER_CATEGORY) {
			return processBeginContact(contact.getFixtureA(), contact.getFixtureB());
		} else if(categoryB == Collision.PLAYER_CATEGORY) {
			return processBeginContact(contact.getFixtureB(), contact.getFixtureA());
		}
		return false;
	}

	private boolean processBeginContact(Fixture player, Fixture target) {
		Body playerBody = player.getBody();

		short targetCategory = target.getFilterData().categoryBits;
		if((targetCategory & Collision.PLAYER_HIT_FILTER_MASK) > 0) {

			// TODO: Move this into some playerHitEventHandler?
			float pushRange = 50f;
			float pushForce = 15000f;

			getPhysicsQueue().add(
					new ExplosionPhysicsMessage(playerBody, pushRange, pushForce,
							Collision.PLAYER_EXPLOSION_MASK));
			// END To do
			if((targetCategory & Collision.ENEMY_CATEGORY) > 0) {
				getEventQueue().send(new PlayerHitEvent(player, target, 1f));
				getEventQueue().send(new EnemyHitEvent(target, player));
				return true;
			} else if((targetCategory & Collision.BULLET_ENEMY_CATEGORY) > 0) {
				WeaponDataI bulletWeapon = (WeaponDataI) target.getUserData();
				getEventQueue().send(
						new PlayerHitEvent(player, target, bulletWeapon.getBulletDamage()));
				getEventQueue().send(
						new DestroyEntityEvent((Entity) target.getBody().getUserData()));
				return true;
			}
		} else if((targetCategory & Collision.PLAYER_PICKUPS) > 0) {
			getEventQueue().send(new PlayerPickupEvent(player, target));
			getEventQueue().send(new DestroyEntityEvent((Entity) target.getBody().getUserData()));
			return true;
		}
		return false;
	}

	@Override
	protected boolean handleEndContact(Contact contact) {
		return false;
	}

	@Override
	protected boolean handlePreSolve(Contact contact, Manifold oldManifold) {
		short categoryA = contact.getFixtureA().getFilterData().categoryBits;
		short categoryB = contact.getFixtureB().getFilterData().categoryBits;
		if(categoryA == Collision.PLAYER_CATEGORY) {
			return processPreSolve(contact, oldManifold, contact.getFixtureA(),
					contact.getFixtureB());
		} else if(categoryB == Collision.PLAYER_CATEGORY) {
			return processPreSolve(contact, oldManifold, contact.getFixtureB(),
					contact.getFixtureA());
		}
		return false;
	}

	private boolean processPreSolve(Contact contact, Manifold oldManifold, Fixture player,
			Fixture target) {
		short targetCategory = target.getFilterData().categoryBits;
		if((targetCategory & Collision.PLAYER_PICKUPS) > 0) {
			contact.setEnabled(false);	// Disable collision from having physical effects on the ship.
			return true;
		}
		return false;
	}

	@Override
	protected boolean handlePostSolve(Contact contact, ContactImpulse impulse) {
		return false;
	}

}
