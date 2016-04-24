//
//  BDPostStatusCell.m
//  BloodDonation
//
//  Created by Le Phuc on 4/24/16.
//  Copyright © 2016 Lê Phúc. All rights reserved.
//

#import "BDPostStatusCell.h"

@implementation BDPostStatusCell

- (void)awakeFromNib {
    [super awakeFromNib];

    [_tvIneedYourHelp.layer setBorderColor: [[UIColor clearColor] CGColor]];
    [_tvIneedYourHelp.layer setBorderWidth: 1.0];
    [_tvIneedYourHelp.layer setCornerRadius:8.0f];
    [_tvIneedYourHelp.layer setMasksToBounds:YES];
    
    // Initialization code
}

- (void)setSelected:(BOOL)selected animated:(BOOL)animated {
    [super setSelected:selected animated:animated];

    // Configure the view for the selected state
}

@end
