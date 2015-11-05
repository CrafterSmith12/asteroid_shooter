class Boom
{
  float x;
  float y;
  float size;
  int timer;
  int duration;

  Boom(float x, float y, float size, float duration)
  {
    this.x = x;
    this.y = y;
    this.size = size * random(1, 4);
    this.duration = (int)(duration * random(1, 4));
    timer = this.duration;
  }

  void draw()
  {
    stroke(aColor, random(50, 100), 0);
    ellipse(x, y, size, size);
  }

  void act()
  {
    if (timer > 0)
    {
      timer--;
    } else
    {
      die();
    }
  }

  void die()
  {
    if (size > BOOM_MIN_SIZE)
    {
      for (int x = 0; x < BOOM_SPLIT; x++)
      {
        float boomX = x + random(-size/2, size/2);
        float boomY = y + random(-size/2, size/2);
        float boomSize = size * BOOM_SCALE;

        booms.add(new Boom(boomX, boomY, boomSize, BOOM_DURATION));
      }
      size = 0;
    }
  }
}