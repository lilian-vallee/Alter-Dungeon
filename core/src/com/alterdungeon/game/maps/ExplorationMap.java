package com.alterdungeon.game.maps;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import java.util.ArrayList;
import java.util.Arrays;

public class ExplorationMap extends TiledMap {

    //Taille de la map
    int MAP_HEIGHT = 100;
    int MAP_WIDTH = 100;

    private Vector3 spawn;

    public Vector3 getSpawn() {
        return spawn;
    }

    /**
     * Constructeur
     * initialise les textures et créé la map a partir d'une matrice.
     */
    public ExplorationMap() {

        //chargement des textures de la map
        Texture tiles = new Texture(Gdx.files.internal("maps/tiles1To10.png"));
        //partitionement des textures
        TextureRegion[][] splitTiles = TextureRegion.split(tiles, 32, 32);

        //creation est initialisation de la map.
        initMap(splitTiles);
    }


    /**
     * Initialise le squelette de la map sous forme de matrice.
     *
     * @return protoMap
     */
    private void initMap(TextureRegion[][] splitTiles) {

        initBackground(splitTiles);

        //creation d'une protomap qui genere et sur le quelle la map sera calque
        int[][] protoMap = new int[MAP_WIDTH][MAP_HEIGHT];

        //initialise toute la protmap a 1(=mur)
        for (int x = 0; x < MAP_WIDTH; x++) {
            for (int y = 0; y < MAP_WIDTH; y++) {
                protoMap[y][x] = 1;
            }
        }

        //ajout des salles, couloir et les entitees qui les composes
        protoMap = initRoom(protoMap);
        //initialise le layer des murs
        initWall(protoMap, splitTiles);

//        //affichage map console
//        for (int x=0 ; x < MAP_WIDTH ; x++){
//            for (int y=0 ; y < MAP_WIDTH ; y++){
//                System.out.print(protoMap[y][x] + " ");
//            }
//            System.out.println();
//        }
    }

    /**
     * initialise le background de la map
     *
     * @param splitTiles
     */
    private void initBackground(TextureRegion[][] splitTiles) {
        TiledMapTileLayer backgroundLayer = new TiledMapTileLayer(MAP_WIDTH, MAP_HEIGHT, 32, 32);

        for (int x = 0; x < backgroundLayer.getWidth(); x++) {
            for (int y = 0; y < backgroundLayer.getHeight(); y++) {

                TiledMapTileLayer.Cell cell = new TiledMapTileLayer.Cell();

                //33% de chance pour un tile alternatif
                if ((int) (Math.random() * 9) == 0) {
                    cell.setTile(new StaticTiledMapTile(splitTiles[1][0]));
                } else {
                    cell.setTile(new StaticTiledMapTile(splitTiles[0][0]));
                }
                backgroundLayer.setCell(y, x, cell);
            }
        }
        backgroundLayer.setName("background");
        this.getLayers().add(backgroundLayer);
    }


    /**
     * créé les différentes salles de la map et les couloirs qui les lies
     * initialise le layer entity qui est composé les différentes entitées interactifs
     *
     * @param protoMap
     * @return
     */
    private int[][] initRoom(int[][] protoMap) {

        int SALLE_MAX = 30;//nombre de salle max autorisé sur la map
        int SALLE_MIN = 15;//nombre de salle min autorisé sur la map
        int TAILLE_MAX = 20;//taille maximum d'une salle en longueur et largeur
        int TAILLE_MIN = 5;//taille minimum d'une salle en longueur et largeur

        //stockage des salles créées
        ArrayList<Room> rooms = new ArrayList<>();
        int nbRooms = SALLE_MIN + (int) (Math.random() * (SALLE_MAX - SALLE_MIN + 1));

        //pour chaque salle de la map
        for (int i = 0; i < nbRooms; i++) {

            //création des paramètres de la salle
            int w = (int) (TAILLE_MIN + Math.random() * (TAILLE_MAX - TAILLE_MIN + 1));//width
            int h = (int) (TAILLE_MIN + Math.random() * (TAILLE_MAX - TAILLE_MIN + 1));//height
            int x = (int) (Math.random() * (MAP_WIDTH - 2 - w) + 1);
            int y = (int) (Math.random() * (MAP_HEIGHT - 2 - h) + 1);

            Room newRoom = new Room(x, y, w, h);

            //On regarde si la nouvelle salle est superposé avec les autres
            boolean isSuperpose = false;

            for (Room r : rooms) {
                if (newRoom.superposer(r)) {
                    isSuperpose = true;
                    i--;//on reduit l'itération du for() pour recommencer l'itération
                    break;//Pas besoin de regarder les autres salles si l'une est deja superpose
                }
            }

            //si isSuperpose est faux on continue le traitement de la salle
            if (!isSuperpose) {

                //les cellules de la salle sont mis à 0
                for (int tx = newRoom.x1; tx < newRoom.x2; tx++) {
                    for (int ty = newRoom.y1; ty < newRoom.y2; ty++) {
                        protoMap[ty][tx] = 0;
                    }
                }

                //liaison de la nouvelle salle avec la précedente
                if (rooms.size() > 0) {
                    protoMap = ajoutCouloir(protoMap, newRoom, rooms.get(rooms.size() - 1));
                } else {
                    spawn = new Vector3(newRoom.centreY, newRoom.centreX, 0);
                    System.out.println(spawn);
                }

                //initEntity(newRoom);

                //ajout de la nouvelle salle de la liste avec les autres
                rooms.add(newRoom);
            }
            //initExit(rooms.get(rooms.size()-1));
        }
        return protoMap;
    }

    /**
     * initialise dans la derniere salle l'entitee de sortie de la map
     *
     * @param room
     */
    private void initExit(Room room) {
        //TODO
    }

    /**
     * initialise les entitees dans la salle
     *
     * @param newRoom
     */
    private void initEntity(Room newRoom) {
        //TODO
    }

    /**
     * @param protoMap
     * @param newRoom
     * @param room
     * @return protoMap
     * ajoute a la proto map les couloirs entre les 2 salles (newRoom et room) par des 0(=sol).
     */
    private int[][] ajoutCouloir(int[][] protoMap, Room newRoom, Room room) {

        //prise des coordonnée x et y qui parte du centre de la nouvelle salle
        int tx = newRoom.centreX;
        int ty = newRoom.centreY;

        //50%
        if ((int) (Math.random() * 2) == 1) {

            //mise a 0(=sol) de toute les cellules de l'axe x jusqu'a la coordonnée x de la salle a relier
            while (tx != room.centreX) {
                protoMap[ty][tx] = 0;
                protoMap[ty + 1][tx] = 0;

                if (newRoom.centreX < room.centreX) {
                    tx++;
                } else {
                    tx--;
                }
            }
            //idem pour l'axe y
            while (ty != room.centreY) {
                protoMap[ty][tx] = 0;
                protoMap[ty][tx + 1] = 0;

                if (newRoom.centreY < room.centreY) {
                    ty++;
                } else {
                    ty--;
                }
            }
        } else {
            //meme methode que precedement mais avec y en premier et x en deuxieme
            while (ty != room.centreY) {
                protoMap[ty][tx] = 0;
                protoMap[ty][tx + 1] = 0;

                if (newRoom.centreY < room.centreY) {
                    ty++;
                } else {
                    ty--;
                }
            }

            while (tx != room.centreX) {
                protoMap[ty][tx] = 0;
                protoMap[ty + 1][tx] = 0;

                if (newRoom.centreX < room.centreX) {
                    tx++;
                } else {
                    tx--;
                }
            }
        }

        return protoMap;
    }


    /**
     * initialise les murs de la map
     *
     * @param protoMap
     * @param splitTiles
     */
    private void initWall(int[][] protoMap, TextureRegion[][] splitTiles) {

        //creation des layers composant les murs
        TiledMapTileLayer sideWallLayer = new TiledMapTileLayer(MAP_WIDTH, MAP_HEIGHT, 32, 32);
        TiledMapTileLayer wallLayer = new TiledMapTileLayer(MAP_WIDTH, MAP_HEIGHT, 32, 32);

        //pour tout les case de la map
        for (int x = MAP_WIDTH - 1; x >= 0; x--) {
            for (int y = 0; y < MAP_HEIGHT; y++) {

                TiledMapTileLayer.Cell cell = new TiledMapTileLayer.Cell();

                if (protoMap[y][x] == 1) {

                    cell.setTile(new StaticTiledMapTile(splitTiles[0][2]));
                    cell.getTile().getProperties().put("blocked", null);
                    wallLayer.setCell(y, x, cell);
                }
                else if (protoMap[y][x] == 0 && protoMap[y][x + 1] == 1) {

                    //33% de chance pour un tile alternatif
                    if ((int) (Math.random() * 6) == 2) {
                        cell.setTile(new StaticTiledMapTile(splitTiles[1][1]));
                    } else {
                        cell.setTile(new StaticTiledMapTile(splitTiles[0][1]));
                    }
                    cell.getTile().getProperties().put("blocked", null);
                    sideWallLayer.setCell(y, x, cell);
                }
            }
        }
        this.getLayers().add(sideWallLayer);
        wallLayer.setName("wall");
        this.getLayers().add(wallLayer);
    }

    public Boolean getCollision(int x , int y){
        TiledMapTileLayer wallLayer = (TiledMapTileLayer) this.getLayers().get("wall");

        try {
            wallLayer.getCell(x, y).getTile().getProperties().containsKey("blocked");
            //System.out.println("blocked");
            return true;
        }
        catch (NullPointerException e){
        }
        return false;
    }



    /**
     * Class Room
     */
    private class Room {
        //coordonnée pour chaque coin de la salle
        int x1;
        int y1;
        int x2;
        int y2;
        //largeur de la salle
        int w;
        int h;

        //coordonnée du point au centre (a peu pres pour les salles avec des nombre pair faut pas deconner
        int centreX;
        int centreY;

        /**
         * Constructeur
         *
         * @param x
         * @param y
         * @param w
         * @param h initialise le reste des attributs d'une salle
         */
        public Room(int x, int y, int w, int h) {
            x1 = x;
            y1 = y;
            x2 = x + w;
            y2 = y + h;
            this.w = w;
            this.h = h;
            centreX = x1 + (w / 2);
            centreY = y1 + (h / 2);
        }

        /**
         * Methode
         *
         * @param room
         * @return Verifie que la room n'est pas superposer avec la room entree en parametre.
         */
        public boolean superposer(Room room) {
            return (x1 <= room.x2 && x2 >= room.x1 && y1 <= room.y2 && y2 >= room.y1);
        }
    }
}