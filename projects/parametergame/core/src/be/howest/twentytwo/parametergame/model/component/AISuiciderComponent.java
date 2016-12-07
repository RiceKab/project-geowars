package be.howest.twentytwo.parametergame.model.component;

// @author Ward Van den Berghe

import be.howest.twentytwo.parametergame.model.physics.events.IPhysicsEvent;
import be.howest.twentytwo.parametergame.model.physics.events.LinearForceEvent;
import be.howest.twentytwo.parametergame.screen.GameScreen;
import be.howest.twentytwo.parametergame.utils.VectorMath;
import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Pool;
import java.util.Collection;
import com.badlogic.gdx.ai.steer.behaviors.Arrive;


public class AISuiciderComponent implements Component, Pool.Poolable {

    
    public static final ComponentMapper<AISuiciderComponent> MAPPER = ComponentMapper.getFor(AISuiciderComponent.class);
    
    
    
    @Override
    public void reset() {
    }

    public void ProcessAI(Entity entity, Collection<IPhysicsEvent> events) {
        
        MovementComponent mc = MovementComponent.MAPPER.get(entity);
        Body body = BodyComponent.MAPPER.get(entity).getBody();
        Vector2 bodyForwardUnitVector = body.getWorldVector(Vector2.Y);
        
			//Vector2 moveForwardVelocity = body.getLinearVelocity();
			//Vector2 maxBodyForwardVec = new Vector2(bodyForwardUnitVector).scl(mc.getMaxLinearVelocity());
			
                        
        // Mainplayer used for testing, 
        Body playerBody = BodyComponent.MAPPER.get(GameScreen.mainPlayer).getBody();
        Vector2 playerPos = playerBody.getPosition();
        Vector2 resultVector =  VectorMath.subtract(playerPos, body.getPosition()).nor().scl(10.0f);//maxBodyForwardVec.sub(moveForwardVelocity).clamp(0f, mc.getLinearAcceleration());
        
        // Move enemy in direction of player
	//events.add(new LinearForceEvent(body, resultVector));
        
        // Turn enemy to look in player direction
        Vector2 forwardDirection = new Vector2((float)Math.cos(body.getAngle() - Math.PI / 2.0f), (float)Math.sin(body.getAngle()- Math.PI / 2.0f));
        Vector2 directionToPlayer = VectorMath.subtract(playerPos, body.getPosition());
        
        float cross = forwardDirection.crs(directionToPlayer);
        float direction = 0;
        if(cross < 0 )
            direction = 1;
        else direction = -1;
        
        float torque = direction * 100.0f;
        
        
        
        //body.applyTorque(torque, true);
        			
	Arrive<Vector2> ar = new Arrive<Vector2>(entity, GameScreen.mainPlayer);
        
        
        
        
    }    
}
