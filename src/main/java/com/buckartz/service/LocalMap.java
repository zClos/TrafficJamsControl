package com.buckartz.service;

import com.buckartz.model.map.City;
import com.buckartz.model.map.Edge;
import com.buckartz.model.map.Road;
import com.buckartz.service.service_map.CityService;
import com.buckartz.service.service_map.EdgeService;
import com.buckartz.service.service_map.RoadService;

import java.util.HashSet;
import java.util.Set;

public class LocalMap {

    private static LocalMap localMap;

    private Double avgMapBackup;
    private Double avgPathBackup;
    private Object[][] mapGrid;
    private Set<Road> path;
    private Set<Road> tmpRoads;

    private final CityService cityService = new CityService();
    private final EdgeService edgeService = new EdgeService();
    private final RoadService roadService = new RoadService();

    public static LocalMap getLocalMapInstance() {
        if (localMap == null) {
            localMap = new LocalMap();
            return localMap;
        } else {
            return localMap;
        }
    }

    private LocalMap() {
        avgMapBackup = 0.0;
        mapGrid = new Object[10][10];
        fillMap();
    }

    public Set<Road> getPath(String sourceCityName, String targetCityName) {
        City sourceCity = cityService.getCity(sourceCityName);
        City targetCity = cityService.getCity(targetCityName);

        if (sourceCity != null && targetCity != null) {
            Set<Road> fastestPath = edgeService.getFastestPath(sourceCity, targetCity, avgMapBackup);
            path = fastestPath;
            path.forEach(road -> {
                if (avgPathBackup != null) {
                    avgPathBackup += road.getBackup();
                } else {
                    avgPathBackup = Double.valueOf(road.getBackup());
                }
                mapGrid[road.getX()][road.getY()] = road;
            });
            avgPathBackup /= path.size();
            return fastestPath;
        } else {
            return null;
        }
    }

    public boolean addEdge(String sourceCityName, String targetCityName) {
        City sourceCity = cityService.getCity(sourceCityName);
        City targetCity = cityService.getCity(targetCityName);

        if (sourceCity != null && targetCity != null) {
            edgeService.addEdge(sourceCity, targetCity, tmpRoads);
            tmpRoads = null;
            return true;
        } else {
            return false;
        }
    }

    public boolean addTmpRoad(Integer x, Integer y, Integer backup) {
        Road road = new Road();
        road.setX(x);
        road.setY(y);
        road.setBackup(backup);
        if (tmpRoads == null) {
            tmpRoads = new HashSet<>();
        }
        if (tmpRoads.add(road)) {
            mapGrid[x][y] = road;
            return true;
        } else {
            return false;
        }
    }

    public boolean addCity(String name, Integer x, Integer y) {
        if (cityService.getCity(name) == null) {
            City city = new City();
            city.setName(name);
            city.setX(x);
            city.setY(y);
            cityService.add(city);
            mapGrid[x][y] = city;
            return true;
        } else {
            return false;
        }
    }

    public void addBackupInfo(String sourceCityName, String targetCityName, Integer x, Integer y, Integer backup) {
        City sourceCity = cityService.getCity(sourceCityName);
        City targetCity = cityService.getCity(targetCityName);
        if (sourceCity != null && targetCity != null) {
            Edge edge = edgeService.getEdge(sourceCity, targetCity);
            if (edge != null) {
                Road road = roadService.getRoad(edge, x, y);
                if (road != null) {
                    road.setBackup(backup);
                    roadService.update(road);
                } else {
                    road = new Road();
                    road.setEdge(edge);
                    road.setX(x);
                    road.setY(y);
                    road.setBackup(backup);
                    edge.getRoadList().add(road);
                    edgeService.updateEdge(edge);
                    mapGrid[x][y] = road;
                }
            }
        }
    }

    private void fillMap() {
        for (Object[] gridRow : mapGrid) {
            for (int i = 0; i < gridRow.length; i++) {
                gridRow[i] = ".";
            }
        }
        Set<Road> roads = roadService.getAll();
        cityService.getAll().forEach(city -> mapGrid[city.getX()][city.getY()] = city);
        roads.forEach(road -> {
            mapGrid[road.getX()][road.getY()] = road;
            Integer roadBackup = road.getBackup();
            if (roadBackup != -1) {
                avgMapBackup += road.getBackup();
            }
        });
        avgMapBackup /= roads.size();
    }

    public void showMap() {
        for (Object[] gridRow : mapGrid) {
            for (Object gridElem : gridRow) {
                if (gridElem instanceof Road) {
                    if (path != null && path.contains((Road) gridElem))
                        System.out.printf("%2s[%2d]W", "", ((Road) gridElem).getBackup());
                    else
                        System.out.printf("%3s[%2d]", "", ((Road) gridElem).getBackup());
                } else {
                    System.out.printf("%7s", gridElem);
                }
            }
            System.out.println("");
        }
        if (avgPathBackup != null) {
            System.out.printf("%s %.2f\n", "Построен маршрут с загруженностью", avgPathBackup);
        }
    }

    public void showEditMap() {
        for (int i = -1; i < mapGrid.length; i++) {
            for (int j = -1; j < mapGrid[(i < 0) ? 0 : i].length; j++) {
                if (i == -1 && j == -1) {
                    System.out.printf("%7s", "x\\y");
                    continue;
                } else if (i == -1) {
                    System.out.printf("%7d", j);
                    continue;
                } else if (j == -1) {
                    System.out.printf("%7d", i);
                    continue;
                }
                if (mapGrid[i][j] instanceof Road) {
                    Road road = (Road) mapGrid[i][j];
                    if (path != null && path.contains(road))
                        System.out.printf("%2s[%2d]W", "", road.getBackup());
                    else
                        System.out.printf("%3s[%2d]", "", road.getBackup());
                } else {
                    System.out.printf("%7s", mapGrid[i][j]);
                }
            }
            System.out.println("");
        }
    }

    public int[] getMapSize() {
        int[] arrSize = {mapGrid.length, mapGrid[0].length};
        return arrSize;
    }

    public Double getAvgMapBackup() {
        return avgMapBackup;
    }

    public Double getAvgPathBackup() {
        return avgPathBackup;
    }
}
