class Asteroid
{
  float x;
  float y;
  float xSpeed;
  float ySpeed;
  float size;
  float health;
  int maxHealth;

  PImage rock;
  PImage damagedRock;

  Asteroid(float x, float y, float size)
  {
    int value;

    value = (int)random(1, 4);
    rock = loadImage("rock" + value + ".png");
    damagedRock = loadImage("rockDamaged" + value + ".png");
    this.x = x;
    this.y = y;
    this.size = size * random(.5, 1.5);
    xSpeed = random(-SPEED_FACTOR, SPEED_FACTOR) / size;
    ySpeed = random(-SPEED_FACTOR, SPEED_FACTOR) / size;
    maxHealth = (int)size;
    health = size;
  }

  void act()
  {
    x =  x + xSpeed;
    y =  y + ySpeed;
    wrap();
  }

  void draw()
  {
    image(rock, x, y, size, size);
    if (health / maxHealth < .5)
    {    
      image(damagedRock, x, y, size, size);
    }
  }

  void wrap()
  {

    if (x >= width)
    {
      x = -size;
    }
    if (y >= height)
    {
      y = -size;
    }
  }
  //void stars()
  //{
  //  for (int x =0; x < 1000; x++)
  //  {
  //    star.get(x).draw();
  //  }
  //}

  void eventListener()
  {
    if (isLeftClicked() == true)
    {
      leftClickEvent();
    }
  }

  boolean isLeftClicked()
  {
    if (mousePressed && mouseX > x && mouseX < x + size && mouseY > y && mouseY < y + size && overHeatTimer == 0)
    {
      return true;
    } else
    {
      return false;
    }
  }

  void leftClickEvent()
  {
    health = health - 5;
    if (health < 0)
    {

      if (size > 50)
      {
        float boomSize = size * BOOM_SCALE;
        float boomX = mouseX + random(-boomSize/2, boomSize/2);
        float boomY = mouseY + random(-boomSize/2, boomSize/2);

        booms.add(new Boom(boomX, boomY, boomSize, BOOM_DURATION));
        spacerock.add(new Asteroid(x, y, size/2)); 
        spacerock.add(new Asteroid(x, y, size/2));
      }
      score++;
      size = 0;
    }
  }
}