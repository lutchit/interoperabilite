package program;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import edu.smu.tspell.wordnet.SynsetType;
import edu.smu.tspell.wordnet.impl.file.Morphology;
import edu.stanford.nlp.trees.Tree;

public class Main {
	
	public static List<Triplet> listeTriplet = new ArrayList<Triplet>();

	public static void main(String[] args) throws IOException {
		System.setProperty("wordnet.database.dir", "dict/");
		
		Files.lines(Paths.get("interopLudo.txt")).forEach(s -> extractTriplet(s));
	}

	private static void extractTriplet(String s) {
		Triplet triplet = new Triplet();
		boolean verbe = false; 
        Parser parser = new Parser(); 
        Tree tree = parser.parse(s);
        Morphology id = Morphology.getInstance();

        List<Tree> leaves = tree.getLeaves();
        // Print words and Pos Tags
        for (Tree leaf : leaves) { 
            Tree parent = leaf.parent(tree);
            if (parent.label().value().length() >= 2) {
            	if((parent.label().value().equals("NNP") || parent.label().value().equals("NN")) && !verbe) {
	            	if(triplet.getSujet() != null)
	            		triplet.setSujet(triplet.getSujet() + " " + leaf.label().value());
	            	else 
	            		triplet.setSujet(leaf.label().value());
	            }
	            else if (parent.label().value().subSequence(0, 2).equals("VB")) {
	            	verbe = true;
	            	String[] attr = id.getBaseFormCandidates(leaf.label().value(), SynsetType.VERB);
	            	if(attr.length > 0)
		            	triplet.setVerbe(attr[0]);
	            	else 
	            		triplet.setVerbe(leaf.label().value());
	            }
	            else if ((parent.label().value().equals("NNP") || parent.label().value().equals("NN")) && verbe) {
	            	if(triplet.getComplement() != null)
	            		triplet.setComplement(triplet.getComplement() + " " + leaf.label().value());
	            	else 
	            		triplet.setComplement(leaf.label().value());
	            }
            }
            // System.out.print(leaf.label().value() + "-" + parent.label().value() + " ");
        }
        listeTriplet.add(triplet);
        System.out.println("Sujet : " + triplet.getSujet() + "  Verbe : " + triplet.getVerbe() + "  Complement : " + triplet.getComplement());
	}
}
