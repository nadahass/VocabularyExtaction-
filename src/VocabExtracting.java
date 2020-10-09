

/*
 * this class aims to extract the most common diana system related words from EGGNOG data. 
 * The audio files of the data are google transcripts  
 * 
 * 
 * author: Nada Alayani 
 * PhD student 
 * Colorado State University 
 * 8 October 2020
 * 
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;

public class VocabExtracting {
	public static void main(String[] args) throws IOException, ClassNotFoundException {

		// Initialize the tagger
		MaxentTagger tagger = new MaxentTagger("taggers/english-bidirectional-distsim.tagger");

		// The sample string
		
		  try (PrintWriter writer = new PrintWriter(new File("Domain_Vocab.csv"))) {
		  
		  StringBuilder sb = new StringBuilder(); StringBuilder sb2 = new
		  StringBuilder();
		  
		  sb.append("DT"); sb.append(','); sb.append("EX"); sb.append(',');
		  sb.append("IN"); sb.append(','); sb.append("JJ"); sb.append(',');
		  sb.append("JJR"); sb.append(','); sb.append("JJS"); sb.append(',');
		  sb.append("NN"); sb.append(','); sb.append("NNS"); sb.append(',');
		  sb.append("PDT"); sb.append(','); sb.append("PRP"); sb.append(',');
		  sb.append("RB"); sb.append(','); sb.append("RBR"); sb.append(',');
		  sb.append("RBS"); sb.append(','); sb.append("RP"); sb.append(',');
		  sb.append("UH"); sb.append(','); sb.append("VB"); sb.append(',');
		  sb.append("VBD"); sb.append(','); sb.append("VBG"); sb.append(',');
		  sb.append("VBZ"); sb.append(','); sb.append("VBP"); sb.append(',');
		  sb.append("WDT"); sb.append(','); sb.append("WP"); sb.append(',');
		  sb.append("WRB"); sb.append('\n'); writer.write(sb.toString());
		  
		  String content= StringreadData();
		 
		
		  //System.out.println("this"+ content); 
		  String tag = ""; String word; int US =0;
		  ArrayList<String> TopN = new ArrayList<String>();
		  
          /*Determiner DT Existential EX Preposition IN Adjectives JJ Adjective
		  comparative JJR Adjective Superlative JJS Noun singular NN noun plural NNS
		  Predeterminer PDT Personal Pronoun PRP Adverb RB Adverb comparative RBR
		  Adverb Superlative RBS Particle RP Interjection UH Verb base form VB Verb
		  Past Dense VBD Verb Gerund VBG Verb 3per_sing VBZ wh determiner WDT wh
		  pronoun WP wh adverb WRB*/
		 
		
		  ArrayList<String> DT = new ArrayList<String>(); ArrayList<String> EX = new
		  ArrayList<String>(); ArrayList<String> IN = new ArrayList<String>();
		  ArrayList<String> JJ = new ArrayList<String>(); ArrayList<String> JJR = new
		  ArrayList<String>(); ArrayList<String> JJS = new ArrayList<String>();
		  ArrayList<String> NN = new ArrayList<String>(); ArrayList<String> NNS = new
		  ArrayList<String>(); ArrayList<String> PDT = new ArrayList<String>();
		  ArrayList<String> PRP = new ArrayList<String>(); ArrayList<String> RB = new
		  ArrayList<String>(); ArrayList<String> RBR = new ArrayList<String>();
		  ArrayList<String> RBS = new ArrayList<String>(); ArrayList<String> RP = new
		  ArrayList<String>(); ArrayList<String> UH = new ArrayList<String>();
		  ArrayList<String> VB = new ArrayList<String>(); ArrayList<String> VBD = new
		  ArrayList<String>(); ArrayList<String> VBG = new ArrayList<String>();
		  ArrayList<String> VBZ = new ArrayList<String>(); ArrayList<String> VBP = new
		  ArrayList<String>(); ArrayList<String> WDT = new ArrayList<String>();
		  ArrayList<String> WP = new ArrayList<String>(); ArrayList<String> WRB = new
		  ArrayList<String>();
		  
		  // The tagged string 
		  String tagged = tagger.tagString(content);
		  String[] tokens = tagged.split("\\s+"); 
		  for (int i = 0; i < tokens.length; i++) { //		  
			  
		  //You may want to check for a non-word character before blindly 
		  // performing a replacement
		  // It may also be necessary to adjust the character class
		  tokens[i] = tokens[i].replaceAll("[^\\w]", ""); US = tokens[i].indexOf("_");
		  
		  // tag and words extraction
		  tag = tokens[i].substring(US + 1, tokens[i].length()); 
		  word =tokens[i].substring(0, US);
		  
		  // build list of words based on their part of speech
		  if (tag.equals("NN")) { NN.add(word); } if
		  (tag.equals("DT")) { DT.add(word); } if (tag.equals("EX")) { EX.add(word); }
		  if (tag.equals("IN")) { IN.add(word); } if (tag.equals("JJ")) { JJ.add(word);
		  } if (tag.equals("JJR")) { JJR.add(word); } if (tag.equals("JJS")) {
		  JJS.add(word); } if (tag.equals("NNS")) { NNS.add(word); } if
		  (tag.equals("PDT")) { PDT.add(word); } if (tag.equals("PRP")) {
		  PRP.add(word); } if (tag.equals("RB")) { RB.add(word); } if
		  (tag.equals("RBR")) { RBR.add(word); } if (tag.equals("RBS")) {
		  RBS.add(word); } if (tag.equals("RP")) { RP.add(word); } if
		  (tag.equals("UH")) { UH.add(word); } if (tag.equals("VB")) { VB.add(word); }
		  if (tag.equals("VBD")) { VBD.add(word); } if (tag.equals("VBG")) {
		  VBG.add(word); } if (tag.equals("VBZ")) { VBZ.add(word); } if
		  (tag.equals("VBP")) { VBP.add(word); } if (tag.equals("WDT")) {
		  WDT.add(word); } if (tag.equals("WP")) { WP.add(word); } if
		  (tag.equals("WRB")) { WRB.add(word); }
		  
		  // test System.out.println(tokens[i] + "--" + tag + "--" + word);
		  } // end for loop
		  
		 // finding the topN words in each list (POS)
		  ArrayList<String> TopNN=topKFrequent(NN,10); ArrayList<String>
		  TopDT=topKFrequent(DT,10); ArrayList<String> TopEX= topKFrequent(EX,10);
		  ArrayList<String> TopIN= topKFrequent(IN,10); ArrayList<String> TopJJ=
		  topKFrequent(JJ,10); ArrayList<String> TopJJR= topKFrequent(JJR,10);
		  ArrayList<String> TopJJS= topKFrequent(JJS,10); ArrayList<String> TopNNS=
		  topKFrequent(NNS,10); ArrayList<String> TopPDT= topKFrequent(PDT,10);
		  ArrayList<String> TopPRP= topKFrequent(PRP,10); ArrayList<String> TopRB=
		  topKFrequent(RB,10); ArrayList<String> ToRBR= topKFrequent(RBR,10);
		  ArrayList<String> TopRBS= topKFrequent(RBS,3); ArrayList<String> TopRP=
		  topKFrequent(RP,10); ArrayList<String> TopUH= topKFrequent(UH,10);
		  ArrayList<String> TopVB= topKFrequent(VB,3); ArrayList<String> TopVBD=
		  topKFrequent(VBD,10); ArrayList<String> TopVBG= topKFrequent(VBG,10);
		  ArrayList<String> TopVBZ= topKFrequent(VBZ,10); ArrayList<String> TopVBP=
		  topKFrequent(VBP,10); ArrayList<String> TopWDT= topKFrequent(WDT,10);
		  ArrayList<String> TopWP= topKFrequent(WP,10); ArrayList<String> TopWRB=
		  topKFrequent(WRB,10);
		  
		  
		  // Writing the top N words into CSV file
		  for ( int i=0; i< TopDT.size(); i++) { sb2.append(TopDT.get(i)+ " / "); }
		  sb2.append(',');
		  for ( int i=0; i< TopEX.size(); i++) {sb2.append(TopEX.get(i)+ " / ");}
		  sb2.append(','); 
		  for ( int i=0; i< TopIN.size(); i++) {sb2.append(TopIN.get(i)+ " / ");}
		  sb2.append(','); 
		  for ( int i=0; i< TopJJ.size(); i++) {sb2.append(TopJJ.get(i)+ " / ");} 
		  sb2.append(','); 
		  for ( int i=0; i< TopJJR.size(); i++) {sb2.append(TopJJR.get(i)+ " / ");}
		  sb2.append(','); 
		  for ( int i=0; i< TopJJS.size(); i++) {sb2.append(TopJJS.get(i)+ " / ");} 
		  sb2.append(','); 
		  for ( int i=0; i< TopNN.size(); i++) {sb2.append(TopNN.get(i)+ " / ");}
		  sb2.append(',');
		  for ( int i=0; i< TopNNS.size(); i++) {sb2.append(TopNNS.get(i)+ " / ");}
		  sb2.append(','); 
		  for ( int i=0; i< TopPDT.size(); i++) {sb2.append(TopPDT.get(i)+ " / ");}
		  sb2.append(','); 
		  for ( int i=0; i< TopPRP.size(); i++) {sb2.append(TopPRP.get(i)+ " / ");} 
		  sb2.append(','); 
		  for ( int i=0; i< TopRB.size(); i++) {sb2.append(TopRB.get(i)+ " / ");}
		  sb2.append(','); 
		  for ( int i=0; i< ToRBR.size(); i++) {sb2.append(ToRBR.get(i)+ " / ");} 
		  sb2.append(','); 
		  for ( int i=0; i< TopRBS.size(); i++) {sb2.append(TopRBS.get(i)+ " / ");}
		  sb2.append(','); 
		  for ( int i=0; i< TopRP.size(); i++) {sb2.append(TopRP.get(i)+ " / "); }
		  sb2.append(','); 
		  for ( int i=0; i< TopUH.size(); i++) {sb2.append(TopUH.get(i)+ " / ");}
		  sb2.append(',');
		  for ( int i=0; i< TopVB.size(); i++) {sb2.append(TopVB.get(i)+ " / "); }
		  sb2.append(','); 
		  for ( int i=0; i< TopVBD.size(); i++) {sb2.append(TopVBD.get(i)+ " / ");}
		  sb2.append(','); 
		  for ( int i=0; i< TopVBG.size(); i++) {sb2.append(TopVBG.get(i)+ " / "); }
		  sb2.append(','); 
		  for ( int i=0; i< TopVBZ.size(); i++) {sb2.append(TopVBZ.get(i)+ " / ");}
		  sb2.append(','); 
		  for ( int i=0; i< TopVBP.size(); i++) {sb2.append(TopVBP.get(i)+ " / ");} 
		  sb2.append(','); 
		  for ( int i=0; i< TopWDT.size(); i++) {sb2.append(TopWDT.get(i)+ " / ");}
		  sb2.append(','); 
		  for ( int i=0; i< TopWP.size(); i++) {sb2.append(TopWP.get(i)+ " / ");}
		  sb2.append(','); 
		  for ( int i=0; i< TopWRB.size(); i++) {sb2.append(TopWRB.get(i)+ " / ");}
		  sb2.append('\n'); 
		  writer.write(sb2.toString()); 
		  sb2.delete(0, sb2.length());
		  System.out.println("Writing to CSV is done!");
		  
		  
		  } catch (FileNotFoundException e) { System.out.println(e.getMessage()); }
		 
	}// end main method



	/* 
	 * This method read list of csv files 
	 * return the content of files in one block of strings
	 */

	public static String StringreadData() throws IOException {
		String FileName = "";
		String content = "";
		String[] columns = null;
		int DOT;
		String exten;
		// ArrayList<String[]> content = new ArrayList<String[]>();

		File pathdir = new File("./data/");
		File[] files = pathdir.listFiles();
		for (int i = 0; i < files.length; i++) {
			DOT = files[i].getName().indexOf(".");
			exten = files[i].getName().substring(DOT + 1, files[i].getName().length());
			if (files[i].isFile() && exten.equals("csv")) { // this line weeds out other
				FileName = files[i].getName();
				System.out.println("FileName: " + FileName);
				int count = 0;
				// content = "";
				try (BufferedReader br = new BufferedReader(new FileReader("./data/" + FileName))) {
					String line = "";
					while ((line = br.readLine()) != null) {
						// System.out.println( line);
						columns = line.split(",");
						content= content.concat(columns[0]);

					}

				} catch (FileNotFoundException e) {
					// Some error logging
				}
			}

		}
				content=content.replaceAll("Transcript", "");

		// System.out.println(content);

		return content;
	}// end method
	
	/* 
	 * This method read list of csv files 
	 * return the content of files in as list of strings for different purposes
	 */ 
	public static List<String> readData() throws IOException {
		String FileName = "";
		List<String> content = new ArrayList<String>();
		String[] columns = null;
		int DOT;
		String exten;
		// ArrayList<String[]> content = new ArrayList<String[]>();

		File pathdir = new File("./data/");
		File[] files = pathdir.listFiles();
		for (int i = 0; i < files.length; i++) {
			DOT = files[i].getName().indexOf(".");
			exten = files[i].getName().substring(DOT + 1, files[i].getName().length());
			if (files[i].isFile() && exten.equals("csv")) { // this line weeds out other
				FileName = files[i].getName();
				System.out.println("FileName: " + FileName);
				int count = 0;
				// content = "";
				try (BufferedReader br = new BufferedReader(new FileReader("./data/" + FileName))) {
					String line = "";
					while ((line = br.readLine()) != null) {
						// System.out.println( line);
						columns = line.split(",");
						content.add(columns[0]);

					}

				} catch (FileNotFoundException e) {
					// Some error logging
				}
			}

		}
		for (int i = 0; i < content.size(); i++) {
			if (content.get(i).equals("Transcript"))
				content.remove(i);
		}

		// System.out.println(content);

		return content;
	}// end method

	
	// returns the topN words in a list of strings
	public static ArrayList<String> topKFrequent(ArrayList<String> words, int k) {
		if (words == null || words.size() == 0) {
			return new ArrayList<String>();
		}

		Map<String, Integer> map = new HashMap();
		for (String s : words) {
			map.put(s, map.getOrDefault(s, 0) + 1);
		}

		List<String>[] bucket = new List[words.size() + 1];
		for (Map.Entry<String, Integer> set : map.entrySet()) {
			int frequecny = set.getValue();
			if (bucket[frequecny] == null) {
				bucket[frequecny] = new ArrayList<>();
			}
			bucket[frequecny].add(set.getKey());
		}

		ArrayList<String> rst = new ArrayList<String>();
		for (int i = bucket.length - 1; i >= 0; i--) {
			if (bucket[i] != null) {
				// Sort the string to meeting demand.
				Collections.sort(bucket[i]);
				// We can use addAll method to avoid adding too much elements (exceeding k).
				for (int j = 0; j < bucket[i].size() && rst.size() < k; j++) {
					rst.add(bucket[i].get(j));
				}
			}
		}
		return rst;
	}

	// return the frequency of words for the entire corpus
	// also it can be used to return the number of words that occur more than n times 
	public static void frequency(List<String> terms) {
		int count = 0;
		Map<String, Integer> result = terms.parallelStream().flatMap(s -> Arrays.asList(s.split(" ")).stream())
				.collect(Collectors.toConcurrentMap(w -> w.toLowerCase(), w -> 1, Integer::sum));
		Object[] values1 = result.values().toArray();
		Object[] key1 = result.keySet().toArray();
		ArrayList<Object> words = new ArrayList<Object>();
		ArrayList<Object> above20 = new ArrayList<Object>();

		// this is used to show the words that occur more than n times, here >20 times
		for (int i = 0; i < result.size(); i++) {
			int v = Integer.parseInt(values1[i].toString());

			if (v >= 20) {
				count++;
				words.add(key1[i]);
				above20.add (v);
			}

		}
		// System.out.println(result);

		System.out.println("words occur more than 20 times: " + count);
		for (int i = 0; i < words.size(); i++) {
		System.out.println(words.get(i) + " : "+ above20.get(i));
		}
	}

}
