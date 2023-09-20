# Space Invaders

## How to play
Run using the gradle command `gradle clean build run`

## Available colours
The colours available to the player are:
* `red`
* `green` and
* `blue`

## Designs Patterns Used
### Factory Method (Creational)
The classes/files involved in this design pattern are:
* `ProjectileFactory` (interface)
* `Projectile` (abstract class)
* `PlayerProjectileFactory`
* `AlienProjectileFactory`
* `PlayerProjectile`
* `AlienProjectile`

### Builder Pattern (Creational)
The classes/files involved in this design pattern are:
* `BunkerEnemyBuilder` (interface)
* `AlienBuilder`
* `BunkerBuilder`
* `Alien`
* `Bunker`
* `Director`

### State Design Pattern (Behavioural)
The classes/files involved in this design pattern are:
* `Bunker`
* `BunkerState` (interface)
* `GreenState`
* `YellowState`
* `RedState`

### Strategy Design Pattern (Behavioural)
The classes/files involved in this design pattern are:
* `ProjectileStrategy` (interface)
* `SlowStraightProjectile`
* `FastStraightProjectile`
* `Alien`
* `Player`