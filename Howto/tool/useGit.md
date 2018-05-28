### Setting up
#### Local
```bash
git config --global user.name
git config --global user.email
git config --global user.name "Pablo"
git config --global user.email "pablo@email.com"
git config --global credential.helper cache  # Set git to use the credential memory cache
git init
```
#### Server
```bash
git clone --depth 1 https://github.com/Jeff-Fang/RepoName.git
git remote show origin
git config --get remote.origin.url
git remote -v  # View remote URLs
git remote set-url origin https://github.com/Jeff-Fang/Udacity-RoboND-P2.git
git remote add origin https://github.com/Jeff-Fang/RepoName.git
git push -u origin master
```

### Project
#### Branch control
```bash
git branch
git branch newBranch
git checkout newBranch
git branch -d branch_name # Delete a branch
git show-branch -a
git rebase master
git push origin --delete <branchName>
git push origin :<branchName>
```
#### Overview
```bash
git status
git log
git status --ignored
git check-ignore (-v) *
```

### Onward
#### Stage
```bash
git add -A
git add .
```
#### Commit
```bash
git commit -m "Initial Commit"
```
#### Push
```bash
git push origin master
```

### Regret
#### Unstage files you might have staged with `git add`
```bash
git reset
git reset --hard HEAD # (works from any subdirectory)
git reset HEAD -- .
git reset HEAD -- path/to/file
```
#### Revert all local uncommitted changes (should be executed in repo root):
```bash
git checkout .
git checkout [some_dir|file]
```
#### Remove all local untracked files, so only git tracked files remain:
```bash
git clean -fdx # WARNING: -x will also remove all ignored files!
```





