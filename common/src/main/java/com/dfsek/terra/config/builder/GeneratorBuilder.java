package com.dfsek.terra.config.builder;

import com.dfsek.terra.biome.palette.PaletteHolder;
import com.dfsek.terra.generation.config.NoiseBuilder;
import com.dfsek.terra.generation.config.WorldGenerator;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import parsii.eval.Scope;

import java.util.Map;

public class GeneratorBuilder {
    private final Map<Long, WorldGenerator> gens = new Object2ObjectOpenHashMap<>();

    private String noiseEquation;

    private String elevationEquation;

    private Scope varScope;

    private Map<String, NoiseBuilder> noiseBuilderMap;

    private PaletteHolder palettes;

    private PaletteHolder slantPalettes;

    private boolean preventInterpolation;

    private boolean interpolateElevation;

    private boolean noise2d;

    private double base;


    public WorldGenerator build(long seed) {
        return gens.computeIfAbsent(seed, k -> new WorldGenerator(seed, noiseEquation, elevationEquation, varScope, noiseBuilderMap, palettes, slantPalettes, interpolateElevation, noise2d, base));
    }

    public boolean isNoise2d() {
        return noise2d;
    }

    public void setNoise2d(boolean noise2d) {
        this.noise2d = noise2d;
    }

    public double getBase() {
        return base;
    }

    public void setBase(double base) {
        this.base = base;
    }

    public String getNoiseEquation() {
        return noiseEquation;
    }

    public void setNoiseEquation(String noiseEquation) {
        this.noiseEquation = noiseEquation;
    }

    public String getElevationEquation() {
        return elevationEquation;
    }

    public void setElevationEquation(String elevationEquation) {
        this.elevationEquation = elevationEquation;
    }

    public Scope getVarScope() {
        return varScope;
    }

    public void setVarScope(Scope varScope) {
        this.varScope = varScope;
    }

    public Map<String, NoiseBuilder> getNoiseBuilderMap() {
        return noiseBuilderMap;
    }

    public void setNoiseBuilderMap(Map<String, NoiseBuilder> noiseBuilderMap) {
        this.noiseBuilderMap = noiseBuilderMap;
    }

    public PaletteHolder getPalettes() {
        return palettes;
    }

    public void setPalettes(PaletteHolder palettes) {
        this.palettes = palettes;
    }

    public PaletteHolder getSlantPalettes() {
        return slantPalettes;
    }

    public void setSlantPalettes(PaletteHolder slantPalettes) {
        this.slantPalettes = slantPalettes;
    }

    public boolean isPreventInterpolation() {
        return preventInterpolation;
    }

    public void setPreventInterpolation(boolean preventInterpolation) {
        this.preventInterpolation = preventInterpolation;
    }

    public void setInterpolateElevation(boolean interpolateElevation) {
        this.interpolateElevation = interpolateElevation;
    }

    public boolean interpolateElevation() {
        return interpolateElevation;
    }
}
