configurations = [[platform: 'linux', jdk: 21]]
if (env.CHANGE_ID == null) { // TODO https://github.com/jenkins-infra/helpdesk/issues/3931 workaround
  configurations += [platform: 'windows', jdk: 17]
}
buildPlugin(
  useContainerAgent: true, // Set to `false` if you need to use Docker for containerized tests
  forkCount: '1C', 
  configurations: configurations
)
