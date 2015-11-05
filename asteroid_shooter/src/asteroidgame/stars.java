class stars
{
  float x;
  float y;
  float size;

  stars()
  {
    x = random(width);
    y = random(height);
    size = random(1, 2);
  }

  void draw()
  {
    fill(255);
    noStroke();
    ellipse(x, y, size, size);
  }
}