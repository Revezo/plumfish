package com.traanite.plumfish.mail;

import lombok.Value;

import java.util.List;

@Value
public class MailRecipients {
    List<String> emails;
}
