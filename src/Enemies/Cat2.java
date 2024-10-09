package Enemies;

import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import Engine.Key;
import Engine.KeyLocker;
import Engine.Keyboard;
import GameObject.Frame;
import GameObject.ImageEffect;
import GameObject.SpriteSheet;
import Level.Player;
import Utils.Point;

import java.util.HashMap;

// This is the class for the Cat player character
// It now has the ability to shoot balls
public class Cat2 extends Player {

    // timer is used to determine how long the cat waits before shooting
    protected int shootWaitTimer;

    // timer is used to determine when a ball is to be shot out
    protected int shootTimer;

    // can be either WALK or SHOOT based on what the cat is currently set to do
    protected CatState catState;
    protected CatState previousCatState;

    // facing direction of the cat
    protected Direction facingDirection;

    protected KeyLocker keyLocker;

 // Increase the y coordinate (starting lower)
    public Cat2(float x, float y) {
        super(new SpriteSheet(ImageLoader.load("Cat.png"), 24, 24), x, y, "STAND_RIGHT");
        gravity = 1f;
        terminalVelocityY = 6f;
        jumpHeight = 25f;
        jumpDegrade = 2f;
        walkSpeed = 1.9f;
        momentumYIncrease = .5f;

        catState = CatState.WALK;
        previousCatState = catState;
        shootWaitTimer = 100;
        facingDirection = Direction.RIGHT;  // Set default direction
        keyLocker = new KeyLocker();

        JUMP_KEY = Key.W;
        MOVE_LEFT_KEY = Key.A;
        MOVE_RIGHT_KEY = Key.D;
        CROUCH_KEY = Key.S;
        System.out.println("yes");
    }


   
    @Override
    public void update() {
        super.update();

        if (currentAnimationName.contains("LEFT")) {
            facingDirection = Direction.LEFT;
        } else if (currentAnimationName.contains("RIGHT")) {
            facingDirection = Direction.RIGHT;
        }

        if (Keyboard.isKeyDown(Key.SHIFT) && !keyLocker.isKeyLocked(Key.SHIFT)) {
            catState = CatState.SHOOT_WAIT;
            keyLocker.lockKey(Key.SHIFT);
        }

        if (Keyboard.isKeyUp(Key.SHIFT)) {
            keyLocker.unlockKey(Key.SHIFT);
        }

        if (catState == CatState.SHOOT_WAIT) {
            if (previousCatState == CatState.WALK) {
                shootTimer = 10;
                currentAnimationName = facingDirection == Direction.RIGHT ? "SHOOT_RIGHT" : "SHOOT_LEFT";
            } else if (shootTimer == 0) {
                catState = CatState.SHOOT;
            } else {
                shootTimer--;
            }
        }

        if (catState == CatState.SHOOT) {
            int ballX;
            float movementSpeed;
            if (facingDirection == Direction.RIGHT) {
                ballX = Math.round(getX()) + getWidth();
                movementSpeed = 2.0f;
            } else {
                ballX = Math.round(getX()) - 5;
                movementSpeed = -2.0f;
            }

            int ballY = Math.round(getY()) + 20;

            Ball ball = new Ball(new Point(ballX, ballY), movementSpeed, 400, this);
            map.addEnemy(ball);

            catState = CatState.WALK;
            shootWaitTimer = 100;
        }

        previousCatState = catState;
    }

    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        int spriteWidth = 15;
        int spriteHeight = 19;

        return new HashMap<String, Frame[]>() {{
            put("STAND_RIGHT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 0))
                    .withScale(3)
                    .withBounds(0, 0, spriteWidth, spriteHeight)
                    .build()
            });

            put("STAND_LEFT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 0))
                    .withScale(3)
                    .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                    .withBounds(0, 0, spriteWidth, spriteHeight)
                    .build()
            });

            put("WALK_RIGHT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(1, 0), 14)
                    .withScale(3)
                    .withBounds(0, 0, spriteWidth, spriteHeight)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(1, 1), 14)
                    .withScale(3)
                    .withBounds(0, 0, spriteWidth, spriteHeight)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(1, 2), 14)
                    .withScale(3)
                    .withBounds(0, 0, spriteWidth, spriteHeight)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(1, 3), 14)
                    .withScale(3)
                    .withBounds(0, 0, spriteWidth, spriteHeight)
                    .build()
            });

            put("WALK_LEFT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(1, 0), 14)
                    .withScale(3)
                    .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                    .withBounds(0, 0, spriteWidth, spriteHeight)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(1, 1), 14)
                    .withScale(3)
                    .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                    .withBounds(0, 0, spriteWidth, spriteHeight)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(1, 2), 14)
                    .withScale(3)
                    .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                    .withBounds(0, 0, spriteWidth, spriteHeight)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(1, 3), 14)
                    .withScale(3)
                    .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                    .withBounds(0, 0, spriteWidth, spriteHeight)
                    .build()
            });

            put("JUMP_RIGHT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(2, 0))
                    .withScale(3)
                    .withBounds(0, 0, spriteWidth, spriteHeight)
                    .build()
            });

            put("JUMP_LEFT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(2, 0))
                    .withScale(3)
                    .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                    .withBounds(0, 0, spriteWidth, spriteHeight)
                    .build()
            });

            put("FALL_RIGHT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(3, 0))
                    .withScale(3)
                    .withBounds(0, 0, spriteWidth, spriteHeight)
                    .build()
            });

            put("FALL_LEFT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(3, 0))
                    .withScale(3)
                    .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                    .withBounds(0, 0, spriteWidth, spriteHeight)
                    .build()
            });

            put("CROUCH_RIGHT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(4, 0))
                    .withScale(3)
                    .withBounds(0, 0, spriteWidth, spriteHeight)
                    .build()
            });

            put("CROUCH_LEFT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(4, 0))
                    .withScale(3)
                    .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                    .withBounds(0, 0, spriteWidth, spriteHeight)
                    .build()
            });

            put("DEATH_RIGHT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(5, 0), 8)
                    .withScale(3)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(5, 1), 8)
                    .withScale(3)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(5, 2), -1)
                    .withScale(3)
                    .build()
            });

            put("DEATH_LEFT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(5, 0), 8)
                    .withScale(3)
                    .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(5, 1), 8)
                    .withScale(3)
                    .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(5, 2), -1)
                    .withScale(3)
                    .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                    .build()
            });

            put("SHOOT_RIGHT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(6, 0))
                    .withScale(3)
                    .withBounds(0, 0, spriteWidth, spriteHeight)
                    .build()
            });

            put("SHOOT_LEFT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(6, 0))
                    .withScale(3)
                    .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                    .withBounds(0, 0, spriteWidth, spriteHeight)
                    .build()
            });
        }};
    }

    public enum Direction {
        LEFT, RIGHT
    }

    public enum CatState {
        WALK, SHOOT_WAIT, SHOOT
    }
}