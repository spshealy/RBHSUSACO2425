import java.io.*;
import java.util.*;

public class Bronze2018Problem3FamilyTree {

    public static void main(String[] args) throws IOException {
        FileInputStream inputStream = new FileInputStream("family.in");
        PrintWriter out = new PrintWriter("family.out");
        out.println(new Bronze2018Problem3FamilyTree().howAreReleated(Input.read(inputStream)));
        out.close();
    }

    public String howAreReleated(Input input) {
        Map<String, Cow> nameToCow = new HashMap<>();
        Cow cow1 = getCowCreateIfNotExists(nameToCow, input.getCow1Name());
        Cow cow2 = getCowCreateIfNotExists(nameToCow, input.getCow2Name());

        for(String[] relationship : input.relationships) {
            Cow mother = getCowCreateIfNotExists(nameToCow, relationship[0]);
            Cow child = getCowCreateIfNotExists(nameToCow, relationship[1]);
            mother.getChildren().add(child);
            child.setMother(mother);
        }
        String r = howRelated(cow1, cow2);
        return r;
    }

    private Cow getCowCreateIfNotExists(Map<String, Cow> nameToCow, String name) {
        Cow cow = nameToCow.get(name);
        if(cow == null) {
            cow = new Cow(name);
            nameToCow.put(name, cow);
        }
        return cow;
    }
    public String howRelated(Cow  cow1, Cow cow2) {
        if(cow1.isSibling(cow2)) return "SIBLINGS";
        List<Cow> bessiesDirectAncestors = cow1.getAllDirectAncestors();

        if(bessiesDirectAncestors.contains(cow2)) { //mother , grandmother, great-grandmother
            return String.format("%s is the %s of %s",cow2.getName(),ancestorRelationShip(bessiesDirectAncestors,cow2),cow1.getName());
        }
        List<Cow> elsiesDirectAncestors = cow2.getAllDirectAncestors();
        if(elsiesDirectAncestors.contains(cow1)) { //mother , grandmother, great-grandmother
            return String.format("%s is the %s of %s",cow1.getName(),ancestorRelationShip(elsiesDirectAncestors,cow1),cow2.getName());
        }

        //now check the childeren of each direct ancestor that is not the mother of bessie to see if it is an ancestor of elsie
        if(bessiesDirectAncestors.size() > 1) {
            List<Cow> nonMotherDirectAncestors = bessiesDirectAncestors.subList(1, bessiesDirectAncestors.size());
            for (Cow ancestor : nonMotherDirectAncestors) {
                if (ancestor.getChildren().contains(cow2)) {
                    return String.format("%s is the %s of %s", cow2.getName(), ancestorChildrenRelationship(nonMotherDirectAncestors, cow2), cow1.getName());
                }
            }
        }
        if(elsiesDirectAncestors.size() > 1) {
            List<Cow> nonMotherDirectAncestors = elsiesDirectAncestors.subList(1, elsiesDirectAncestors.size());
            for (Cow ancestor : nonMotherDirectAncestors) {
                if (ancestor.getChildren().contains(cow1)) {
                    return String.format("%s is the %s of %s", cow1.getName(), ancestorChildrenRelationship(nonMotherDirectAncestors, cow1), cow2.getName());
                }
            }
        }


        Set<Cow> bessiesAncestors = cow1.getAllAncestors();
        Set<Cow> elsiesAncestors = cow2.getAllAncestors();
        Set<Cow> commonAncestors = new HashSet<>(bessiesAncestors);
        commonAncestors.retainAll(elsiesAncestors);
        if(commonAncestors.size() > 0) {
            return "COUSINS";
        }
        return "NOT RELATED";
    }
    public String ancestorRelationShip(List<Cow> directAncestors, Cow c) {
        int location = directAncestors.indexOf(c);
        if(location == -1) return null;
        String relation = null;
        for(int i=0;i<=location;i++) {
            if(i==0) {relation = "mother";}
            else if(i==1) {relation = "grand-" + relation;}
            else {
                relation = "great-" + relation;
            }

        }
        return relation;
    }

    public String ancestorChildrenRelationship(List<Cow> directAncestors, Cow c) {
        StringBuilder sb = new StringBuilder();
        for(int directAncesotrIndex = 0;directAncesotrIndex<directAncestors.size(); directAncesotrIndex++){
            List<Cow> ancestorChildren = directAncestors.get(directAncesotrIndex).getChildren();
            int location = ancestorChildren.indexOf(c);
            if(location!=-1) {
                // we found a child for a direct ancestor so its and "aunt" relationship
                    int numGreats = directAncesotrIndex;
                    for(int i=0;i<numGreats;i++) {
                        sb.append("great-");
                    }
                    sb.append("aunt");
                    break;
            }
        }
        return sb.toString();
    }

    public static class Cow {
        private String name;
        private Cow mother;
        private List<Cow> children ;

        public Cow(String name) {
            this.name = name;
            children = new ArrayList<>();
        }

        public String getName() {
            return name;
        }
        public List<Cow> getChildren() {
            return children;
        }

        public Cow getMother() {
            return mother;
        }

        public void setMother(Cow mother) {
            this.mother = mother;
        }

        public boolean isSibling(Cow cow) {
            if (this.getMother() == null || cow.getMother() == null) return false;
            return this.mother.equals(cow.mother);
        }

        public List<Cow> getAllDirectAncestors() {
            List<Cow> ancestors = new ArrayList<>();
            Cow current = this;
            while(current.getMother() != null) {
                ancestors.add(current.getMother());
                current = current.getMother();
            }
            return ancestors;
        }

        public Set<Cow> getAllAncestors() {
            Set<Cow> ancestors = new HashSet<>();
            List<Cow> directAncestors = getAllDirectAncestors();
            for(Cow ancestor : directAncestors) {
                ancestors.add(ancestor);
                ancestors.addAll(ancestor.getAllDescendants());
            }
            return ancestors;
        }
        public Set<Cow> getAllDescendants() {
            Set<Cow> descendants = new HashSet<>();
            addDescendants(descendants);
            return descendants;
        }
        private void addDescendants(Set<Cow> descendants) {
            for(Cow child : children) {
                descendants.add(child);
                child.addDescendants(descendants);
            }
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            Cow cow = (Cow) o;
            return this.getName().equals(cow.getName());
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(name);
        }
    }

    static class Input {
        private String cow1Name;
        private String cow2Name;
        private String[][] relationships;

        public Input(String cow1Name, String cow2Name, String[][] relationships) {
            this.cow1Name = cow1Name;
            this.cow2Name = cow2Name;
            this.relationships = relationships;
        }

        public String getCow1Name() {
            return cow1Name;
        }

        public String[][] getRelationships() {
            return relationships;
        }

        public String getCow2Name() {
            return cow2Name;
        }

        static Input read(InputStream inputStream) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            StringTokenizer st = new StringTokenizer(br.readLine());
            int numCows = Integer.parseInt(st.nextToken());
            String elsieName = st.nextToken();
            String bessieName = st.nextToken();
            String[][] relationships = new String[numCows][2];
            for (int i = 0; i < numCows; i++) {
                st = new StringTokenizer(br.readLine());
                relationships[i][0] = st.nextToken();
                relationships[i][1] = st.nextToken();
            }
            return new Input(elsieName, bessieName, relationships);
        }
    }
}
