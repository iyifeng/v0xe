package com.v0ex.concurrency.practice;

import java.util.*;

/**
 * Animals
 * <p/>
 * Thread confinement of local primitive and reference variables
 *
 * @author Brian Goetz and Tim Peierls
 */
public class Animals {

    Ark ark;

    Species species;

    Gender gender;

    public int loadTheArk(Collection<Animal> candidates){
        SortedSet<Animal> animals;
        int numPairs = 0;
        Animal candidate = null;
        //animals confined to method,dont let them escape;
        animals = new TreeSet<Animal>(new SpeciesGenderComparator());
        animals.addAll(candidates);
        for (Animal animal : animals){
            if (candidate == null || !candidate.isPotentialMate(animal)){
                candidate = animal;
            }else {
                ark.load(new AnimalPair(candidate,animal));
                ++numPairs;
                candidate = null;
            }
        }
        return numPairs;
    }

    class SpeciesGenderComparator implements Comparator<Animal>{

        @Override
        public int compare(Animal one, Animal two) {
            int speciesCompare = one.species.compareTo(two.species);
            return speciesCompare != 0 ? speciesCompare : one.gender.compareTo(two.gender);
        }
    }

    class Animal{
        Species species;
        Gender gender;
        public boolean isPotentialMate(Animal other){
            return species == other.species && gender != other.gender;
        }
    }

    class AnimalPair{
        private final Animal one ,two;
        public AnimalPair(Animal one,Animal two){
            this.one = one;
            this.two = two;
        }
    }

    class Ark{
        private final Set<AnimalPair> loadedAnimals = new HashSet<>();
        public void load(AnimalPair animalPair){
            loadedAnimals.add(animalPair);
        }
    }

    enum Species {
        AARDVARK, BENGAL_TIGER, CARIBOU, DINGO, ELEPHANT, FROG, GNU, HYENA,
        IGUANA, JAGUAR, KIWI, LEOPARD, MASTADON, NEWT, OCTOPUS,
        PIRANHA, QUETZAL, RHINOCEROS, SALAMANDER, THREE_TOED_SLOTH,
        UNICORN, VIPER, WEREWOLF, XANTHUS_HUMMINBIRD, YAK, ZEBRA
    }

    enum Gender {
        MALE, FEMALE
    }

}
