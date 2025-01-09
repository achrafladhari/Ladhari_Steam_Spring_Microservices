#!/bin/bash
PRIVATE_KEY="$HOME/.ssh/vm_rsa"
PUBLIC_KEY="$HOME/.ssh/vm_rsa.pub"
ssh-keygen -t rsa -b 4096 -f "$PRIVATE_KEY" -N "" -C "pfe2024-key"
echo "Generated keys: $PRIVATE_KEY and $PUBLIC_KEY"