package sbtavrohugger;

import avrohugger.filesorter.{ AvdlFileSorter, AvscFileSorter }
import avrohugger.Generator
import java.io.File

import sbt.Keys._
import sbt.{ globFilter, singleFileFinder, Logger }
import sbt.Path._

object FileWriter {

  private[sbtavrohugger] def generateCaseClasses(generator: Generator, srcDirs: Seq[File], target: File, log: Logger): Set[java.io.File] = {
    log.info("Considering source directories %s".format(srcDirs.mkString(",")))
    def getSrcFiles(dirs: Seq[File], fileExtension: String) = for {
      srcDir  <- dirs
      srcFile <- (srcDir ** s"*.$fileExtension").get
    } yield srcFile

    generator.clear()
    for (inFile <- AvscFileSorter.sortSchemaFiles(getSrcFiles(srcDirs, "avsc"))) {
      log.info("Compiling AVSC %s to %s".format(inFile, target.getPath))
      generator.fileToFile(inFile, target.getPath)
    }

    generator.clear()
    for (idlFile <- AvdlFileSorter.sortSchemaFiles(getSrcFiles(srcDirs, "avdl"))) {
      log.info("Compiling Avro IDL %s".format(idlFile))
      generator.fileToFile(idlFile, target.getPath)
    }

    generator.clear()
    for (inFile <- getSrcFiles(srcDirs, "avro")) {
      log.info("Compiling Avro datafile %s".format(inFile))
      generator.fileToFile(inFile, target.getPath)
    }

    generator.clear()
    for (protocol <- getSrcFiles(srcDirs, "avpr")) {
      log.info("Compiling Avro protocol %s".format(protocol))
      generator.fileToFile(protocol, target.getPath)
    }

    generator.clear()
    (target ** ("*.java" | "*.scala")).get.toSet
  }

}
