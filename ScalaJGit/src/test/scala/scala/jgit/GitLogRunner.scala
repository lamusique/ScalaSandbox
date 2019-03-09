package scala.jgit

import org.eclipse.jgit.storage.file.FileRepositoryBuilder
import better.files._
import java.io.{File => JFile}
import java.util.Date

import better.files.Dsl._
import org.eclipse.jgit.api.{CreateBranchCommand, Git, ListBranchCommand}
import org.eclipse.jgit.api.errors.GitAPIException
import org.eclipse.jgit.diff.{DiffEntry, DiffFormatter, RawTextComparator}
import org.eclipse.jgit.lib.Constants
import org.eclipse.jgit.revwalk.RevCommit
import org.eclipse.jgit.treewalk.CanonicalTreeParser
import org.eclipse.jgit.util.io.DisabledOutputStream

import collection.JavaConverters._

object GitLogRunner extends App {

    // TODO change the path
    val git = Git.open(File("[git repo path]/.git").toJava)
    val repo = git.getRepository

    val branchRefs = git.branchList.setContains("HEAD").setListMode(ListBranchCommand.ListMode.ALL).call.asScala
    branchRefs foreach println

    val log = git.log().call()

    val df = new DiffFormatter(DisabledOutputStream.INSTANCE)
    df.setRepository(repo)
    df.setDiffComparator(RawTextComparator.DEFAULT)
    df.setDetectRenames(true)


    val reader = repo.newObjectReader

    log.iterator.asScala.take(10).drop(9).foreach(commit => {
        println("-" * 64)
        println(commit)
        println(commit.getAuthorIdent.getWhen)
        // This is the same as commiter time
        println(new Date(commit.getCommitTime * 1000L))
        println(commit.getCommitterIdent.getWhen)
        println(commit.getAuthorIdent)
        println(commit.getShortMessage)

        println("==== revs ====")
        val revs = git.nameRev.add(commit.getId).call.asScala
        revs foreach println

        val curParser = new CanonicalTreeParser
        curParser.reset(reader, commit.getTree)

        val prevParser = new CanonicalTreeParser
        prevParser.reset(reader, commit.getParent(0).getTree)

        val diffFormatter = new DiffFormatter(System.out)
        diffFormatter.setRepository(repo)
        val entries = diffFormatter.scan(curParser, prevParser)
        diffFormatter.format(entries)


//        val entries = get(commit, df)
//        val diff = entries.flatMap(entry => {
//            entry
//            val edits = df.toFileHeader(entry).toEditList.asScala
//
//            df.format()
//        })
//        println(diff.mkString("\n"))

    })


    def get(c: RevCommit, df: DiffFormatter) =
        c.getParentCount match {
            case 0 => List.empty[DiffEntry]
            case _ => df.scan(c.getParent(0), c.getTree).asScala.toList
        }

}

