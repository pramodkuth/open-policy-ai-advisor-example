package agentpolicy

default allow = false

allow if{
    not contains(input.userPrompt,"delete")
}

allow if{
    input.tool != "deleteUser"
}
