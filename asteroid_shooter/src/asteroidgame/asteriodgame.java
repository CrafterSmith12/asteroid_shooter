final int NUM_STARS = 1000;
final float ASTEROID_BASE_SIZE = 128;
final float ASTEROID_NUMBER = 20;
final float SPEED_FACTOR = 200;
final float LASER_MAX_HEAT = 50;
final float LASER_HEAT_RATE = .75;
final float LASER_COOL_RATE = 1;
final int LASER_OVERHEAT_PENALTY = 60;
final float BOOM_SCALE = .15;      
final int BOOM_DURATION = 30;
final int BOOM_AMOUNT = 10;
final int BOOM_SPLIT = 2;
final int BOOM_MIN_SIZE = 5;

PImage stard1;
PImage stard2;
PImage dstar;
PImage naboo;
PImage hilt;
PImage endgame;

ArrayList<Asteroid> spacerock;
ArrayList <stars> star;
ArrayList <Boom> booms;

int score = 0;
int aColor;
int numAsteroid = 20;
int gameTimer = 1200;
float x;
float y;
float size;
float curHeat = 0;
int overHeatTimer = 0;


void setup()
{
  noCursor();
  fullScreen();
  background(0);
  cursor(CROSS);
  size = random(32, 128);
  aColor = (int)(random(150, 255));
  stard1 = loadImage("http://madsoft.hu/jquery/levitation/images/stardestroyer.png");
  stard2 = loadImage("http://vignette3.wikia.nocookie.net/disney/images/c/c0/Star_Destroyer_Render.png");
  dstar = loadImage("http://vignette4.wikia.nocookie.net/disney/images/5/53/Death_Star_Render.png");
  naboo = loadImage("http://img4.wikia.nocookie.net/__cb20121011040100/starwars/images/1/18/Ord_Mantell_TOR_new.png");
  hilt = loadImage("http://dragonbox.ca/images_sabres/sw_obiwan_ep3_mini.gif");
  endgame = loadImage("endgame.png");

  spacerock = new ArrayList<Asteroid>();
  star = new ArrayList<stars>();
  booms = new ArrayList<Boom>();

  for (int x =0; x < ASTEROID_NUMBER; x++)
  {
    spacerock.add(new Asteroid(random(width), random(height), ASTEROID_BASE_SIZE));
  }
  for (int x =0; x < NUM_STARS; x++)
  {
    star.add(new stars());
  }
}

void draw()
{
  background(0);
  stars();
  starwars();
  asteroids();
  display();
  laser();
  end();
  if (gameTimer > 0)
  {
    gameTimer--;
  }
}

void display()
{
  final float HEAT_BAR_X = 615;    // x position
  final float HEAT_BAR_Y = 30;    // y position
  final float HEAT_BAR_WIDTH = 200;    // width at max heat
  final float HEAT_BAR_HEIGHT = 15;    // height 
  textSize(50);
  fill(255, 226, 0);
  text("Score: " + score, 20, 50);

  textSize(50);
  if (gameTimer > 11*60)
  {
    fill(255, 226, 0);
    text("Time: " + gameTimer/60, 1200, 50);
  } else
  {
    fill(255, random(100), random(100));
    text("Time: " + gameTimer/60, 1200, 50);
  }
  fill(43, 84, 108);
  stroke(43, 84, 108);
  rect(HEAT_BAR_X, HEAT_BAR_Y, HEAT_BAR_WIDTH, HEAT_BAR_HEIGHT);

  fill(16, 150, 232);
  stroke(43, 84, 108);
  rect(HEAT_BAR_X, HEAT_BAR_Y, (curHeat / LASER_MAX_HEAT) * HEAT_BAR_WIDTH, HEAT_BAR_HEIGHT);

  image(hilt, 495, 3, 125, 70);
}

void laser()
{
  stroke(255);
  line(0, mouseY, width, mouseY);
  line(mouseX, 0, mouseX, height);

  if (overHeatTimer > 0)
  {
    overHeatTimer--;
  } else if (mousePressed)
  {
    noCursor();
    fill(aColor, random(50, 100), 0);
    stroke(aColor, random(50, 100), 0);
    ellipse(mouseX, mouseY, 15, 15);
    curHeat = curHeat + LASER_HEAT_RATE;

    if (curHeat > LASER_MAX_HEAT)
    {
      overHeatTimer = LASER_OVERHEAT_PENALTY;
      cursor(CROSS);
    }
  } else
  {
    noCursor();
    cursor(CROSS);
    curHeat = curHeat - LASER_COOL_RATE;
    if (curHeat < 0)
    {
      curHeat = 0;
    }
  }
}

void asteroids()
{
  for (int x = 0; x < spacerock.size(); x++)
  {
    spacerock.get(x).draw();
    spacerock.get(x).act();
    spacerock.get(x).eventListener();
  }
}

void stars()
{
  for (int x = 0; x < NUM_STARS; x++)
  {
    star.get(x).draw();
  }
}

void boom()
{
  for (int x = 0; x < BOOM_AMOUNT; x++)
  {
    booms.get(x).draw();
    booms.get(x).act();
  }
}

void starwars()
{
  image(dstar, 300, 200, 216, 200);
  image(stard1, 200, 550, 237, 215);
  image(stard2, 800, 320, 388, 243);
  image(naboo, 800, 775, 975, 975);
}

void end()
{
  if (gameTimer == 0)
  {
    clear();
    noCursor();
    background(0);
    stars();
    image(endgame, 400, 200);
    textSize(50);
    fill(255, 226, 0);
    text("Score: " + score, 600, 620);
  }
}

void stop()
{
  if (keyPressed && key == ESC)
  {
    stop();
  }
}