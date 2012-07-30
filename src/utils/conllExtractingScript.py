#Author : David Przybilla
#Date: July 2012 14:21
#Input:This scripts takes as an input the conll corpus annotated with name entities
#Output: a file for each entity type x, each file  contains all entities of type x in the corpus
from sets import Set

PATH_TO_CORPUS_FILE="/media/46DC342BDC34179F/CONLL/conll03-NER/eng.train"

class ConllReader:
		def __init__(self,pathToCorpus):
				#path of hte corpus
				self.path=pathToCorpus
				#set storig all entity types
				self.entityTypes=Set()
				#dictionary key:EntityType, Value=SetOFEntities of that type
				self.files={}

				#original file with no annotations
				self.originalFile=""

				#read the corpus and separate the entities
				self.readFile()

				

				#write the list of entities
				self.writeFiles()

		#write the list into files
		def writeFiles(self):
			#get the types of entities
			for key in self.files.keys():

				content=""
				listOfEntities=self.files[key]
				for entity in listOfEntities:
					content=content+entity+"\n"
				file_=open(key,'w')
				file_.write(content)
			file_=open('originalCorpus','w')
			file_.write(self.originalFile)



		#adds a new entity to an entity type
		def addEntity(self,entityType,entity):
			if((not entityType=="") and (not entity=="")):
				entityType=entityType[2:]
				if(entityType in self.files):
					self.files[entityType].add(entity)
				else:
					self.files[entityType]=Set()
					self.files[entityType].add(entity)

		#reads the file and store all information
		def readFile(self):
			corpusFile=open(self.path,'r')
			#read all the lines of the corpus
			currentEntity={}
			currentEntity['type']=""
			currentEntity['entity']=""
			previousType=""
			for line in corpusFile.readlines():

				line=line.strip()
				if(line==""):
					self.originalFile=self.originalFile+"\n"
				if(line!=""):
					partsOfLine=line.split(" ")
					entityType=partsOfLine[3].strip()
					partOfEntity=partsOfLine[0].strip()

					#get the original text
					self.originalFile=self.originalFile+" "+partsOfLine[0]
					
					#if the type of the curreent entity is hte same as the previous line then gather them as one entity
					

					if(entityType!="O" and (entityType==previousType or (previousType[0]=='B' and entityType[2:]==previousType[2:]) )):
						currentEntity['entity']=currentEntity['entity']+" "+partOfEntity
						currentEntity['entity']=currentEntity['entity'].strip()
						currentEntity['type']=entityType
					else:
						#otherwise add the previos entity and start hte formation of a new one
						self.addEntity(currentEntity['type'],currentEntity['entity'])
						currentEntity={}
						if(entityType!="O"):
							currentEntity['type']=entityType
							currentEntity['entity']=partOfEntity
						else:
							currentEntity['type']=""
							currentEntity['entity']=""

					previousType=entityType


reader=ConllReader(PATH_TO_CORPUS_FILE)